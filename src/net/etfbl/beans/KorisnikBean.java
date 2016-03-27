package net.etfbl.beans;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import net.etfbl.dto.Korisnik;
import net.etfbl.dao.*;

@ManagedBean(name = "korisnikBean")
public class KorisnikBean {
	private Korisnik korisnik;
	private Korisnik prijavljeniKorisnik;
	private String pretragaKorisnika;
	private String prijavaKorisnickoIme;
	private String prijavaLozinka;
	private List<Korisnik> korisnici;

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
		KorisnikDAO.insertKorisnik(korisnik);
		this.obrisiPolja();
		return true;
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
	
	public String prijavi() throws NoSuchAlgorithmException, SQLException
	{
		String stranica = "";
		prijavljeniKorisnik = KorisnikDAO.login(prijavaKorisnickoIme, prijavaLozinka);
		if (prijavljeniKorisnik != null)
			stranica = "userpage";
		else
			stranica = "front_page";
		
		return stranica;
	}
	
	public String noviPutopis()
	{
		return "newTravel";
	}
}
