package net.etfbl.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import net.etfbl.dao.*;
import net.etfbl.dto.Putopis;

@ManagedBean(name="putopisBean")
public class PutopisBean {
	private String tekstPretrage;
	private List<Putopis> putopisi = null;

	public String getTekstPretrage() {
		return tekstPretrage;
	}

	public void setTekstPretrage(String tekstPretrage) {
		this.tekstPretrage = tekstPretrage;
	}
	
	public void pretrazi()
	{
		setPutopisi(new ArrayList<Putopis>());
		setPutopisi(PutopisDAO.getByTravel(tekstPretrage));
	}

	public List<Putopis> getPutopisi() {
		return putopisi;
	}

	public void setPutopisi(List<Putopis> putopisi) {
		this.putopisi = putopisi;
	}
	
	public void setPutopisiForGuest(List<Putopis> putopisi)
	{
		for(Putopis p : putopisi)
		{
			p.setTekstPutopisa(getFirstParagraph(p.getTekstPutopisa()));
		}
		this.putopisi = putopisi;
	}
	
	public static String getFirstParagraph(String putopis)
	{
		String[] paragrafi = putopis.split("(\n+)+");
		
		return paragrafi[0];
	}
}
