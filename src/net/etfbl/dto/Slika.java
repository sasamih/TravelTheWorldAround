package net.etfbl.dto;

public class Slika {
	
	private int idSlike;
	private String putanjaSlike;
	private Korisnik korisnik;
	private int statusSlika;
	
	
	public int getIdSlike() {
		return idSlike;
	}
	public void setIdSlike(int idSlike) {
		this.idSlike = idSlike;
	}
	public String getPutanjaSlike() {
		return putanjaSlike;
	}
	public void setPutanjaSlike(String putanjaSlike) {
		this.putanjaSlike = putanjaSlike;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public int getStatusSlika() {
		return statusSlika;
	}
	public void setStatusSlika(int statusSlika) {
		this.statusSlika = statusSlika;
	}
}
