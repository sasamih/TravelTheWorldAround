package net.etfbl.dto;

public class OcjenaSlike {
	private int idOcjene;
	private Slika slika;
	private Korisnik korisnik;
	private int ocjena = 0;
	
	public int getIdOcjene() {
		return idOcjene;
	}
	public void setIdOcjene(int idOcjene) {
		this.idOcjene = idOcjene;
	}
	public Slika getSlika() {
		return slika;
	}
	public void setSlika(Slika slika) {
		this.slika = slika;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public int getOcjena() {
		return ocjena;
	}
	public void setOcjena(int ocjena) {
		this.ocjena = ocjena;
	}
}
