package net.etfbl.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import net.etfbl.dto.Korisnik;
import net.etfbl.dao.*;

@ManagedBean(name = "korisnikBean")
public class KorisnikBean {
	private Korisnik korisnik;
	private String imeKorisnika;
	private List<Korisnik> korisnici;

	public KorisnikBean()
	{
		korisnik = new Korisnik();
	}
	
	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	public boolean insert()
	{
		return KorisnikDAO.insertKorisnik(korisnik);
	}

	public List<Korisnik> getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(List<Korisnik> korisnici) {
		this.korisnici = korisnici;
	}

	public String getImeKorisnika() {
		return imeKorisnika;
	}

	public void setImeKorisnika(String imeKorisnika) {
		this.imeKorisnika = imeKorisnika;
	}
	
	public void pretrazi()
	{
		korisnici = new ArrayList<Korisnik>();
		korisnici = KorisnikDAO.getByName(imeKorisnika);
	}
}
