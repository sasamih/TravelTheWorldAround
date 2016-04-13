package net.etfbl.beans;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import net.etfbl.Utility;
import net.etfbl.dao.*;
import net.etfbl.dto.Korisnik;
import net.etfbl.dto.Putopis;

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
	
	private static List<Putopis> putopisiUCekanju;
	private static List<Putopis> putopisiKorisnika;
	private Putopis putopisCekanje;

	public String getTekstPretrage() {
		return tekstPretrage;
	}

	public void setTekstPretrage(String tekstPretrage) {
		this.tekstPretrage = tekstPretrage;
	}
	
	public void pretrazi() throws IOException
	{
		setPutopisi(new ArrayList<Putopis>());
		setPutopisi(PutopisDAO.getByTravel(tekstPretrage));
		getTekstPutopisa();
	}
	
	public void pretraziGost() throws IOException
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
	
	public void kreirajPDF() throws DocumentException, IOException
	{
		// step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/home/sasa/pdf.pdf"));
        // step 3
        document.open();
        // step 4
        Utility.setPutanjaDoProjekta();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(Utility.projectPath + "/newTravel.xhtml")); 
        //step 5
         document.close();
	}
}
