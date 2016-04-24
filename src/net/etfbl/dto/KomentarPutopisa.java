package net.etfbl.dto;

public class KomentarPutopisa {
	
	private int idKomentara;
	private Korisnik korisnik;
	private Putopis putopis;
	private String tekstKomentara;
	
	public int getIdKomentara() {
		return idKomentara;
	}
	public void setIdKomentara(int idKomentara) {
		this.idKomentara = idKomentara;
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
	public String getTekstKomentara() {
		return tekstKomentara;
	}
	public void setTekstKomentara(String tekstKomentara) {
		this.tekstKomentara = tekstKomentara;
	}
}
