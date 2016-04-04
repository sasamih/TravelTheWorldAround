package net.etfbl.dto;

public class Poruka {

	private String posiljalac;
	private String primalac;
	private String tekstPoruke;
	private int statusProcitana;
	private String vrijemeSlanja;
	
	public String getPosiljalac() {
		return posiljalac;
	}
	public void setPosiljalac(String posiljalac) {
		this.posiljalac = posiljalac;
	}
	public String getPrimalac() {
		return primalac;
	}
	public void setPrimalac(String primalac) {
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
