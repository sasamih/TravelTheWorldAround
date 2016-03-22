package net.etfbl.dto;

public class Slika {

	private int id;
	private String naziv;
	private String datum;
	private String mjesto;
	private String opis;
	private String putanja;
	private Korisnik korisnik;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getMjesto() {
		return mjesto;
	}
	public void setMjesto(String mjesto) {
		this.mjesto = mjesto;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getPutanja() {
		return putanja;
	}
	public void setPutanja(String putanja) {
		this.putanja = putanja;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	
}
