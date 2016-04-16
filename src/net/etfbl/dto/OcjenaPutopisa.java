package net.etfbl.dto;

public class OcjenaPutopisa {

	private int idOcjene;
	private Putopis putopis;
	private Korisnik korisnik;
	private int ocjena;
	
	public int getIdOcjene() {
		return idOcjene;
	}
	public void setIdOcjene(int idOcjene) {
		this.idOcjene = idOcjene;
	}
	public Putopis getPutopis() {
		return putopis;
	}
	public void setPutopis(Putopis putopis) {
		this.putopis = putopis;
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
