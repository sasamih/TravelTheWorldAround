package net.etfbl.dto;

public class Poruka {

	private int idPoruke;
	private Korisnik posiljalac;
	private Korisnik primalac;
	private String tekstPoruke;
	private int statusProcitana;
	private String vrijemeSlanja;
	

	public int getIdPoruke() {
		return idPoruke;
	}
	public void setIdPoruke(int idPoruke) {
		this.idPoruke = idPoruke;
	}
	public Korisnik getPosiljalac() {
		return posiljalac;
	}
	public void setPosiljalac(Korisnik posiljalac) {
		this.posiljalac = posiljalac;
	}
	public Korisnik getPrimalac() {
		return primalac;
	}
	public void setPrimalac(Korisnik primalac) {
		this.primalac = primalac;
	}
	public String getTekstPoruke() {
		return tekstPoruke;
	}
	public void setTekstPoruke(String tekstPoruke) {
		this.tekstPoruke = tekstPoruke;
	}
	public int getStatusProcitana() {
		return statusProcitana;
	}
	public void setStatusProcitana(int statusProcitana) {
		this.statusProcitana = statusProcitana;
	}
	public String getVrijemeSlanja() {
		return vrijemeSlanja;
	}
	public void setVrijemeSlanja(String vrijemeSlanja) {
		this.vrijemeSlanja = vrijemeSlanja;
	}
}
