package net.etfbl.travel;

public class Korisnik {
	private String ime;
	private String prezime;
	private String korisnickoIme;
	private String lozinka;
	private String datumRodjenja;
	private String kratkaBiografija;
	private String eMail;
	private String korisnickaGrupa;
	private int status;
	
	public Korisnik()
	{
		super();
	}
	
	public Korisnik(String ime, String korisnickoIme, String lozinka, String prezime, String eMail, String kratkaBiografija, String datumRodjenja, String korisnickaGrupa)
	{
		this.korisnickaGrupa = korisnickaGrupa;
		this.datumRodjenja = datumRodjenja;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.eMail = eMail;
		this.kratkaBiografija = kratkaBiografija;
		this.korisnickoIme = korisnickoIme;
	}
	
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
	public String getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public String getKratkaBiografija() {
		return kratkaBiografija;
	}
	public void setKratkaBiografija(String kratkaBiografija) {
		this.kratkaBiografija = kratkaBiografija;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getKorisnickaGrupa() {
		return korisnickaGrupa;
	}
	public void setKorisnickaGrupa(String korisnickaGrupa) {
		this.korisnickaGrupa = korisnickaGrupa;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
