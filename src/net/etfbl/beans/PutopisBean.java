package net.etfbl.beans;

import java.io.*;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.rpc.ServiceException;

import org.primefaces.event.ToggleEvent;

import net.etfbl.Utility;
import net.etfbl.dao.*;
import net.etfbl.dto.KomentarPutopisa;
import net.etfbl.dto.Korisnik;
import net.etfbl.dto.OcjenaPutopisa;
import net.etfbl.dto.Poruka;
import net.etfbl.dto.Putopis;
import net.etfbl.travel.TopTravels;
import net.etfbl.travel.TopTravelsService;
import net.etfbl.travel.TopTravelsServiceLocator;

import com.itextpdf.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

@ManagedBean(name="putopisBean")
@SessionScoped
public class PutopisBean {
	private String tekstPretrage;
	private List<Putopis> putopisi = null;
	private Putopis noviPutopis = new Putopis();
	
	private String kljucneRijeci;
	private String prikaziSakrijKomentar;
	private static boolean prikazi = true;
	
	private static List<Putopis> putopisiUCekanju;
	private static List<Putopis> putopisiKorisnika;
	private ArrayList<net.etfbl.travel.Putopis> topTen;
	private Putopis putopisCekanje;

	private ArrayList<OcjenaPutopisa>  ocjeneKorisnika = null;
	private ArrayList<OcjenaPutopisa> ocjenePutopisa = null;
	
	private ArrayList<KomentarPutopisa> komentariPutopisa = null;
	
	private String noviKomentar;
	
	
	private ArrayList<Putopis> putopisiZaPeriod;
	private ArrayList<Putopis> najviseDjeljeni;
	private int ukupnoPutopisa;
	private int ukupnoPrihvacenih;
	private int ukupnoOdbijenih;
	
	public String getTekstPretrage() {
		return tekstPretrage;
	}

	public void setTekstPretrage(String tekstPretrage) {
		this.tekstPretrage = tekstPretrage;
	}
	
	public void pretrazi() throws IOException, SQLException
	{
		setPutopisi(new ArrayList<Putopis>());
		setPutopisi(PutopisDAO.getByTravel(tekstPretrage));
		ocjeneKorisnika = new ArrayList<OcjenaPutopisa>();
		for(Putopis p : putopisi)
		{
			if (Utility.prijavljeniKorisnik != null)
			{
				OcjenaPutopisa ocjena = OcjenaPutopisaDAO.getGradesByUserAndTravel(p, Utility.prijavljeniKorisnik);
				if (ocjena != null)
				{
					ocjeneKorisnika.add(ocjena);
				}
				else
				{
					OcjenaPutopisa ocjenaTmp = new OcjenaPutopisa();
					ocjenaTmp.setKorisnik(Utility.prijavljeniKorisnik);
					ocjenaTmp.setPutopis(p);
					ocjenaTmp.setOcjena(0);
					ocjeneKorisnika.add(ocjenaTmp);
				}
			}
		}
		getTekstPutopisa();
	}
	
	public void pretraziGost() throws IOException, SQLException
	{
		pretrazi();
		setPutopisiForGuest(putopisi);
	}

	public List<Putopis> getPutopisi() {
		return putopisi;
	}

	public void setPutopisi(List<Putopis> putopisi) {
		this.putopisi = putopisi;
	}
	
	public ArrayList<net.etfbl.travel.Putopis> getTopTen() {
		return topTen;
	}

	public void setTopTen(List<net.etfbl.travel.Putopis> topTen) {
		this.topTen = (ArrayList<net.etfbl.travel.Putopis>) topTen;
	}

	public Putopis getNoviPutopis() {
		return noviPutopis;
	}

	public void setNoviPutopis(Putopis noviPutopis) {
		this.noviPutopis = noviPutopis;
	}
	
	public String insert() throws FileNotFoundException, SQLException
	{
		noviPutopis.setPutanja("/WEB-INF/putopisi/" + noviPutopis.getNazivPutopisa() + ".txt");
		if (Utility.projectPath.equals(""))
		{
			Utility.setPutanjaDoProjekta();
		}
		
		Calendar c = Calendar.getInstance();
		noviPutopis.setDatumObjavljivanja(c.get(Calendar.DAY_OF_MONTH) + "." 
				+ (c.get(Calendar.MONTH)+1) + "." + c.get(Calendar.YEAR) + ".");
		
		PrintWriter pw = new PrintWriter(new File(Utility.projectPath + noviPutopis.getPutanja()));
		pw.println(noviPutopis.getTekstPutopisa());
		pw.close();
		
		noviPutopis.setKorisnik(Utility.prijavljeniKorisnik);
		
		String[] rijeci = kljucneRijeci.split(",");
		
		if (Utility.newTravel)
		{
			PutopisDAO.insert(noviPutopis, rijeci);
		}
		else
		{
			PutopisDAO.update(noviPutopis, rijeci);
		}
		return "userpage";
	}

	public String odobriPutopis(Putopis putopis) throws SQLException
	{
		if (putopis != null)
		{
			putopisiUCekanju.remove(putopis);
			putopis.setStatus(1);
			PutopisDAO.updateStatus(putopis);
			
			Calendar c = Calendar.getInstance();
			Poruka poruka = new Poruka();
			poruka.setVrijemeSlanja(c.get(Calendar.DAY_OF_MONTH) + "." 
					+ (c.get(Calendar.MONTH)+1) + "." + c.get(Calendar.YEAR) + ".");
			poruka.setPosiljalac(Utility.prijavljeniKorisnik);
			poruka.setPrimalac(putopis.getKorisnik());
			poruka.setTekstPoruke("Vas putopis je odobren");
			poruka.setStatusProcitana(0);
			PorukaDAO.insert(poruka);
		}
		
		return "adminpage";
	}
	
	public String izmjeniPutopis(Putopis putopis) throws IOException, SQLException
	{
		noviPutopis = putopis;
		Utility.newTravel = false;
		noviPutopis.setTekstPutopisa("");
		getTekstJednogPutopisa(noviPutopis);
		List<String> rijeci = PutopisDAO.getKeyWords(noviPutopis.getIdPutopisa());
		kljucneRijeci = "";
		for (String rijec : rijeci)
		{
			kljucneRijeci += rijec + ",";
		}
		
		return "newTravel";
	}
	
	public String putopisInfo(Putopis putopis) throws IOException
	{
		putopis.setTekstPutopisa("");
		getTekstJednogPutopisa(putopis);
		setPutopisCekanje(putopis);
		
		return "travelinfopage";
	}
	
	public String putopisProcitaj(Putopis putopis) throws IOException
	{
		putopis.setTekstPutopisa("");
		getTekstJednogPutopisa(putopis);
		setPutopisCekanje(putopis);
		
		return "readTravel";
	}
	
	public void setPutopisiForGuest(List<Putopis> putopisi)
	{
		for(Putopis p : putopisi)
		{
			p.setTekstPutopisa(getFirstParagraph(p.getTekstPutopisa()));
		}
		this.putopisi = putopisi;
	}
	
	public static String getFirstParagraph(String putopis)
	{
		String[] paragrafi = putopis.split("(\n+)+");

		return paragrafi[0];
	}
	
	private void getTekstPutopisa() throws IOException
	{
		if (putopisi != null)
		{
			for (Putopis p : putopisi)
			{
				getTekstJednogPutopisa(p);
			}
		}
	}
	
	private static void getTekstJednogPutopisa(Putopis p) throws IOException
	{
		Utility.setPutanjaDoProjekta();
		BufferedReader br = new BufferedReader(new FileReader(Utility.projectPath + p.getPutanja()));
		
		String newLine = "";
		while ((newLine = br.readLine()) != null)
		{
			if (newLine.equals(""))
				p.dodajTekstPutopisa("\n");
			p.dodajTekstPutopisa(newLine);
		}
		br.close();
	}
	
	public String getKljucneRijeci() {
		return kljucneRijeci;
	}

	public void setKljucneRijeci(String kljucneRijeci) {
		this.kljucneRijeci = kljucneRijeci;
	}

	public static void getTravelsOnHold() throws SQLException
	{
		putopisiUCekanju = new ArrayList<Putopis>();
		putopisiUCekanju = PutopisDAO.getTravelsOnHold();
	}
	
	public static void getTravelsFromKorisnik(Korisnik korisnik) throws SQLException, IOException
	{
		putopisiKorisnika = new ArrayList<Putopis>();
		putopisiKorisnika = PutopisDAO.getTravelsByUser(korisnik);
	}
	
	public void getOcjeneKorisnika(Korisnik korisnik) throws SQLException
	{
		ocjeneKorisnika = OcjenaPutopisaDAO.getGradesByUser(korisnik);
	}
	
	public void getOcjenePutopisa(Putopis putopis) throws SQLException
	{
		ocjenePutopisa = new ArrayList<OcjenaPutopisa>();
		ocjenePutopisa = OcjenaPutopisaDAO.getGradesByTravel(putopis);
	}
	
	public String dobaviKomentarePutopisa() throws SQLException
	{
		if (prikazi)
		{
			komentariPutopisa = KomentarPutopisDAO.getCommentsByTravel(putopisCekanje);
			prikazi = false;
			prikaziSakrijKomentar = "Sakrij";
		}
		else
		{
			komentariPutopisa = null;
			prikazi = true;
			prikaziSakrijKomentar = "Prikazi komentare";
		}
		
		return "readTravel";
	}
	
	public void promjeniOcjenu(OcjenaPutopisa ocjena) throws SQLException
	{
		for(OcjenaPutopisa op : ocjeneKorisnika)
		{
			if (op.getPutopis().getIdPutopisa() == ocjena.getPutopis().getIdPutopisa())
			{
				if (OcjenaPutopisaDAO.getById(ocjena) == null)
				{
					OcjenaPutopisaDAO.insert(ocjena);
				}
				else
				{
					OcjenaPutopisaDAO.update(ocjena);
				}
				break;
			}
		}
		ArrayList<OcjenaPutopisa> prosjekOcjene = OcjenaPutopisaDAO.getGradesByTravel(ocjena.getPutopis());
		double prosjek = 0.0;
		int suma = 0;
		for (OcjenaPutopisa op : prosjekOcjene)
		{
			suma += op.getOcjena();
		}
		prosjek = suma/(double)prosjekOcjene.size();
		ocjena.getPutopis().setProsjecnaOcjena(prosjek);
		PutopisDAO.updateProsjecnaOcjena(ocjena.getPutopis());
	}
	
	public String dodajKomentar() throws SQLException
	{
		KomentarPutopisa komentar = new KomentarPutopisa();
		komentar.setKorisnik(Utility.prijavljeniKorisnik);
		komentar.setPutopis(putopisCekanje);
		komentar.setTekstKomentara(noviKomentar);
		KomentarPutopisDAO.insert(komentar);
		noviKomentar = null;
		
		return "readTravel";
	}
	
	public String napraviIzvjestaj() throws SQLException
	{
		ukupnoPrihvacenih = PutopisDAO.getCount(1);
		ukupnoOdbijenih = PutopisDAO.getCount(0);
		ukupnoPutopisa = ukupnoOdbijenih + ukupnoPrihvacenih;
		
		return "adminpage";
	}
	
	public List<Putopis> getPutopisiUCekanju() {
		return putopisiUCekanju;
	}

	public void setPutopisiUCekanju(List<Putopis> putopisiUCekanju) {
		PutopisBean.putopisiUCekanju = putopisiUCekanju;
	}

	public Putopis getPutopisCekanje() {
		return putopisCekanje;
	}

	public void setPutopisCekanje(Putopis putopisCekanje) {
		this.putopisCekanje = putopisCekanje;
	}

	public List<Putopis> getPutopisiKorisnika() {
		return putopisiKorisnika;
	}

	public void setPutopisiKorisnika(List<Putopis> putopisiKorisnika) {
		PutopisBean.putopisiKorisnika = putopisiKorisnika;
	}
	
	public ArrayList<OcjenaPutopisa> getOcjeneKorisnika() {
		return ocjeneKorisnika;
	}

	public void setOcjeneKorisnika(ArrayList<OcjenaPutopisa> ocjeneKorisnika) {
		this.ocjeneKorisnika = ocjeneKorisnika;
	}

	public ArrayList<OcjenaPutopisa> getOcjenePutopisa() {
		return ocjenePutopisa;
	}

	public void setOcjenePutopisa(ArrayList<OcjenaPutopisa> ocjenePutopisa) {
		this.ocjenePutopisa = ocjenePutopisa;
	}

	public ArrayList<KomentarPutopisa> getKomentariPutopisa() {
		return komentariPutopisa;
	}

	public void setKomentariPutopisa(ArrayList<KomentarPutopisa> komentariPutopisa) {
		this.komentariPutopisa = komentariPutopisa;
	}

	public void kreirajPDF() throws DocumentException, IOException
	{
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/home/rtrk/" + noviPutopis.getNazivPutopisa() + ".pdf"));
        document.open();
        Utility.setPutanjaDoProjekta();
        File file = new File(Utility.projectPath + "/testHtml.html");
        if (!file.exists())
        {
        	file.createNewFile();
        	PrintWriter pw = new PrintWriter(file);
        	pw.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">" +
        				"<head>" +
        				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />" +
        				"<title>Insert title here</title>" +
						"</head>" +
						"<body>" +
						"<h1>" + noviPutopis.getNazivPutopisa() + "</h1>" +
						"<p>" + noviPutopis.getTekstPutopisa() + "</p>" +
						"</body>" +
						"</html>");
        	pw.close();
        }
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(Utility.projectPath + "/testHtml.html"));
        file.delete();
        document.close();
	}
	
	public void prijava()
	{
		tekstPretrage = "";
		putopisi = null;
		ocjeneKorisnika = null;
	}
	
	public void odjava()
	{
		tekstPretrage = "";
		ocjeneKorisnika = null;
		putopisi = null;
	}
	
	public String nazad()
	{
		if (Utility.prijavljeniKorisnik != null)
		{
			return "userpage";
		}
		else
		{
			return "front_page";
		}
	}
	
	public void topTenTravels() throws ServiceException, RemoteException
	{
		TopTravelsServiceLocator loc= new TopTravelsServiceLocator();
		TopTravels service = loc.getTopTravels();
		topTen = new ArrayList<net.etfbl.travel.Putopis>();
		net.etfbl.travel.Putopis[] topTenObj = null;
		
		if (service != null)
			topTenObj = service.getTopTenTravels();
		
		for (net.etfbl.travel.Putopis o : topTenObj)
		{
			topTen.add(o);
		}
	}

	public String getPrikaziSakrijKomentar() {
		if (prikazi)
			prikaziSakrijKomentar = "Prikazi komentare";
		else
			prikaziSakrijKomentar = "Sakrij";
		return prikaziSakrijKomentar;
	}

	public void setPrikaziSakrijKomentar(String prikaziSakrijKomentar) {
		this.prikaziSakrijKomentar = prikaziSakrijKomentar;
	}

	public String getNoviKomentar() {
		return noviKomentar;
	}

	public void setNoviKomentar(String noviKomentar) {
		this.noviKomentar = noviKomentar;
	}

	public ArrayList<Putopis> getPutopisiZaPeriod() {
		return putopisiZaPeriod;
	}

	public void setPutopisiZaPeriod(ArrayList<Putopis> putopisiZaPeriod) {
		this.putopisiZaPeriod = putopisiZaPeriod;
	}

	public ArrayList<Putopis> getNajviseDjeljeni() {
		return najviseDjeljeni;
	}

	public void setNajviseDjeljeni(ArrayList<Putopis> najviseDjeljeni) {
		this.najviseDjeljeni = najviseDjeljeni;
	}

	public int getUkupnoPutopisa() {
		return ukupnoPutopisa;
	}

	public void setUkupnoPutopisa(int ukupnoPutopisa) {
		this.ukupnoPutopisa = ukupnoPutopisa;
	}

	public int getUkupnoPrihvacenih() {
		return ukupnoPrihvacenih;
	}

	public void setUkupnoPrihvacenih(int ukupnoPrihvacenih) {
		this.ukupnoPrihvacenih = ukupnoPrihvacenih;
	}

	public int getUkupnoOdbijenih() {
		return ukupnoOdbijenih;
	}

	public void setUkupnoOdbijenih(int ukupnoOdbijenih) {
		this.ukupnoOdbijenih = ukupnoOdbijenih;
	}
}
