package net.etfbl.dto;

import javax.faces.bean.ManagedBean;

public class Korisnik {
	
	private String ime;
	private String prezime;
	private String email;
	private String biografija;
	private String datumRodjenja;
	private String korisnickaGrupa;
	private String korisnickoIme;
	private String lozinka;
	
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBiografija() {
		return biografija;
	}
	public void setBiografija(String biografija) {
		this.biografija = biografija;
	}
	public String getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public String getKorisnickaGrupa() {
		return korisnickaGrupa;
	}
	public void setKorisnickaGrupa(String korisnickaGrupa) {
		this.korisnickaGrupa = korisnickaGrupa;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
	
	
}
