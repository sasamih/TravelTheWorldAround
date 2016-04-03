package net.etfbl.beans;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.etfbl.Utility;
import net.etfbl.dto.Korisnik;
import net.etfbl.dto.Putopis;
import net.etfbl.dao.*;

@ManagedBean(name = "korisnikBean")
//@ViewScoped
public class KorisnikBean {
	private Korisnik korisnik;
	private Korisnik prijavljeniKorisnik;
	private String pretragaKorisnika;
	private String prijavaKorisnickoIme;
	private String prijavaLozinka;
	private List<Korisnik> korisnici;
	
	private String ponovljenaLozinka;
	private String lozinkeNejednake;
	
	private String porukaRegistracija = "";
	private String porukaPrijava = "";
	
	private List<Korisnik> naloziUCekanju;

	public KorisnikBean()
	{
		korisnik = new Korisnik();
		prijavljeniKorisnik = new Korisnik();
	}
	
	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	public Korisnik getPrijavljeniKorisnik() {
		return prijavljeniKorisnik;
	}

	public void setPrijavljeniKorisnik(Korisnik prijavljeniKorisnik) {
		this.prijavljeniKorisnik = prijavljeniKorisnik;
	}

	public boolean insert() throws NoSuchAlgorithmException
	{
		try {
			if (!korisnikExists())
			{
				KorisnikDAO.insertKorisnik(korisnik);
				setPorukaRegistracija("Vas nalog ceka na odobrenje od administratora.");
				this.obrisiPolja();
				return true;
			}
			else
			{
				setPorukaRegistracija("Korisnicko ime vec postoji.");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}

	public boolean korisnikExists() throws SQLException
	{
		if (KorisnikDAO.exists(korisnik.getKorisnickoIme()))
			return true;
		return false;
	}
	
	public List<Korisnik> getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(List<Korisnik> korisnici) {
		this.korisnici = korisnici;
	}
	
	public void pretrazi()
	{
		korisnici = new ArrayList<Korisnik>();
		korisnici = KorisnikDAO.getByName(pretragaKorisnika);
	}
	
	public void obrisiPolja()
	{
		korisnik.setIme(null);
		korisnik.setPrezime(null);
		korisnik.setDatumRodjenja(null);
		korisnik.seteMail(null);
		korisnik.setKratkaBiografija(null);
		korisnik.setKorisnickoIme(null);
		korisnik.setLozinka(null);
	}

	public String getPretragaKorisnika() {
		return pretragaKorisnika;
	}

	public void setPretragaKorisnika(String pretragaKorisnika) {
		this.pretragaKorisnika = pretragaKorisnika;
	}

	public String getPrijavaLozinka() {
		return prijavaLozinka;
	}

	public void setPrijavaLozinka(String prijavaLozinka) {
		this.prijavaLozinka = prijavaLozinka;
	}

	public String getPrijavaKorisnickoIme() {
		return prijavaKorisnickoIme;
	}

	public void setPrijavaKorisnickoIme(String prijavaKorisnickoIme) {
		this.prijavaKorisnickoIme = prijavaKorisnickoIme;
	}
	
	public String getPonovljenaLozinka() {
		return ponovljenaLozinka;
	}

	public void setPonovljenaLozinka(String ponovljenaLozinka) {
		this.ponovljenaLozinka = ponovljenaLozinka;
	}

	public String getLozinkeNejednake() {
		return lozinkeNejednake;
	}

	public void setLozinkeNejednake(String lozinkeNejednake) {
		this.lozinkeNejednake = lozinkeNejednake;
	}

	public String getPorukaRegistracija() {
		return porukaRegistracija;
	}

	public void setPorukaRegistracija(String porukaRegistracija) {
		this.porukaRegistracija = porukaRegistracija;
	}

	public String getPorukaPrijava() {
		return porukaPrijava;
	}

	public void setPorukaPrijava(String porukaPrijava) {
		this.porukaPrijava = porukaPrijava;
	}

	public String prijavi() throws NoSuchAlgorithmException, SQLException
	{
		String stranica = "";
		prijavljeniKorisnik = KorisnikDAO.login(prijavaKorisnickoIme, prijavaLozinka);
		Utility.prijavljeniKorisnik = prijavljeniKorisnik;
		if (prijavljeniKorisnik != null)
			if (prijavljeniKorisnik.getKorisnickaGrupa().equals("korisnik"))
			{
				stranica = "userpage";
			}
			else
			{
				naloziUCekanju = new ArrayList<Korisnik>();
				naloziUCekanju = KorisnikDAO.getUsersOnHold();
				PutopisBean.getTravelsOnHold();
				stranica = "adminpage";
			}
		else
		{
			stranica = "front_page";
			porukaPrijava = "Vas nalog ceka na odobrenje administratora.";
		}
		//System.out.println("1." + prijavljeniKorisnik.getKorisnickoIme());
		return stranica;
	}
	
	public String odjava()
	{
		prijavljeniKorisnik = null;
		prijavljeniKorisnik = new Korisnik();
		
		return "front_page";
	}
	
	public String deaktiviraj() throws SQLException
	{
		//System.out.println("2." + Utility.prijavljeniKorisnik.getKorisnickoIme());
		KorisnikDAO.deactivate(Utility.prijavljeniKorisnik);
		Utility.prijavljeniKorisnik = null;
		odjava();
		
		return "front_page";
	}
	
	public String noviPutopis()
	{
		return "newTravel";
	}
	
	public String odobriKorisnika()//Korisnik korisnik) throws SQLException
	{
		System.out.println("Bio");
		//KorisnikDAO.activate(korisnik);
		return "adminpage";
	}

	////////////////////////////////////////////////////////////////////////////////
	private String loz;
	
	public void sacuvajLozinku()
	{
		loz = korisnik.getLozinka();
		System.out.println(loz);
	}
	
	public void provjeriLozinke()
	{
		System.out.println("AAA>>>" + korisnik.getLozinka());
		if (korisnik.getLozinka() != null)
		{
			sacuvajLozinku();
		}
		
		System.out.println(loz + ">>" + ponovljenaLozinka);
		if ("sasa".equals(ponovljenaLozinka))
		{
			lozinkeNejednake = "Lozinke se poklapaju.";
		}
		else
			lozinkeNejednake = "Lozinke se ne poklapaju.";
	}
	/////////////////////////////////////////////////////////////////////////////////////////////

	public List<Korisnik> getNaloziUCekanju() {
		return naloziUCekanju;
	}

	public void setNaloziUCekanju(ArrayList<Korisnik> naloziUCekanju) {
		this.naloziUCekanju = naloziUCekanju;
	}
	
}
