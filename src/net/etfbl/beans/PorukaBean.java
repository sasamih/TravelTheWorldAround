package net.etfbl.beans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.etfbl.Utility;
import net.etfbl.dao.PorukaDAO;
import net.etfbl.dto.Poruka;

@ManagedBean(name = "porukaBean")
@SessionScoped
public class PorukaBean {

	private Poruka novaPoruka;
	private String tekstPoruke;
	private ArrayList<Poruka> neprocitanePoruke;
	
	public String posaljiPoruku() throws SQLException
	{
		Calendar c = Calendar.getInstance();
		novaPoruka = new Poruka();
		novaPoruka.setVrijemeSlanja(c.get(Calendar.DAY_OF_MONTH) + "." 
				+ (c.get(Calendar.MONTH)+1) + "." + c.get(Calendar.YEAR) + ".");
		novaPoruka.setPosiljalac(Utility.prijavljeniKorisnik);
		novaPoruka.setPrimalac(Utility.primalacPoruke);
		Utility.primalacPoruke = null;
		novaPoruka.setStatusProcitana(0);
		novaPoruka.setTekstPoruke(tekstPoruke);
		PorukaDAO.insert(novaPoruka);
		novaPoruka = null;
		tekstPoruke = null;
		
		return "userpage";
	}
	
	public void dobaviNeprocitanePoruke() throws SQLException
	{
		neprocitanePoruke = PorukaDAO.getAllUnreadMessages(Utility.prijavljeniKorisnik);
		if (neprocitanePoruke == null)
		{
			neprocitanePoruke = new ArrayList<Poruka>();
		}
	}
	
	public String oznaciProcitano(Poruka poruka) throws SQLException
	{
		PorukaDAO.update(poruka);
		neprocitanePoruke.remove(poruka);
		
		return "userpage";
	}
	
	public Poruka getNovaPoruka() {
		return novaPoruka;
	}

	public void setNovaPoruka(Poruka novaPoruka) {
		this.novaPoruka = novaPoruka;
	}

	public String getTekstPoruke() {
		return tekstPoruke;
	}

	public void setTekstPoruke(String tekstPoruke) {
		this.tekstPoruke = tekstPoruke;
	}

	public ArrayList<Poruka> getNeprocitanePoruke() {
		return neprocitanePoruke;
	}

	public void setNeprocitanePoruke(ArrayList<Poruka> neprocitanePoruke) {
		this.neprocitanePoruke = neprocitanePoruke;
	}
}
