package net.etfbl.dto;

public class Album {
	
	private int idAlbuma;
	private Korisnik korisnik;
	private String nazivAlbuma;
	private Putopis putopis;
	
	public int getIdAlbuma() {
		return idAlbuma;
	}
	public void setIdAlbuma(int idAlbuma) {
		this.idAlbuma = idAlbuma;
	}
	public String getNazivAlbuma() {
		return nazivAlbuma;
	}
	public void setNazivAlbuma(String nazivAlbuma) {
		this.nazivAlbuma = nazivAlbuma;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public Putopis getPutopis() {
		return putopis;
	}
	public void setPutopis(Putopis putopis) {
		this.putopis = putopis;
	}
}
