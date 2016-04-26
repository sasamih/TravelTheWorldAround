package net.etfbl.dto;

public class KomentarSlike {
	
	private int idKomentara;
	private Korisnik korisnik;
	private Slika slika;
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
	public Slika getSlika() {
		return slika;
	}
	public void setSlika(Slika slika) {
		this.slika = slika;
	}
	public String getTekstKomentara() {
		return tekstKomentara;
	}
	public void setTekstKomentara(String tekstKomentara) {
		this.tekstKomentara = tekstKomentara;
	}
}
