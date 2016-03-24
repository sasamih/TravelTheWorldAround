package net.etfbl.dto;

public class Putopis {
	private int idPutopisa;
	private String nazivPutopisa;
	private String datumObjavljivanja;
	private String podaciOMjestu;
	private String tekstPutopisa;
	private Korisnik korisnik;
	
	public int getIdPutopisa() {
		return idPutopisa;
	}
	public void setIdPutopisa(int idPutopisa) {
		this.idPutopisa = idPutopisa;
	}
	public String getNazivPutopisa() {
		return nazivPutopisa;
	}
	public void setNazivPutopisa(String nazivPutopisa) {
		this.nazivPutopisa = nazivPutopisa;
	}
	public String getDatumObjavljivanja() {
		return datumObjavljivanja;
	}
	public void setDatumObjavljivanja(String datumObjavljivanja) {
		this.datumObjavljivanja = datumObjavljivanja;
	}
	public String getPodaciOMjestu() {
		return podaciOMjestu;
	}
	public void setPodaciOMjestu(String podaciOMjestu) {
		this.podaciOMjestu = podaciOMjestu;
	}
	public String getTekstPutopisa() {
		return tekstPutopisa;
	}
	public void setTekstPutopisa(String tekstPutopisa) {
		this.tekstPutopisa = tekstPutopisa;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	
	@Override
	public String toString()
	{
		return "Id putopisa:" + idPutopisa + " Naziv putopisa:" + nazivPutopisa +  " Tekst putopisa: " + tekstPutopisa;
	}
}
