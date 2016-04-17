package net.etfbl.beans;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.etfbl.dao.OcjenaPutopisaDAO;
import net.etfbl.dto.Korisnik;
import net.etfbl.dto.OcjenaPutopisa;
import net.etfbl.dto.Putopis;

@ManagedBean(name = "ocjenaPutopisaBean")
public class OcjenaPutopisBean {

	private static ArrayList<OcjenaPutopisa>  ocjeneKorisnika = new ArrayList<OcjenaPutopisa>();
	private static ArrayList<OcjenaPutopisa> ocjenePutopisa = new ArrayList<OcjenaPutopisa>();
	
	public ArrayList<OcjenaPutopisa> getOcjeneKorisnika() {
		return ocjeneKorisnika;
	}

	public void setOcjeneKorisnika(ArrayList<OcjenaPutopisa> ocjeneKorisnika) {
		this.ocjeneKorisnika = ocjeneKorisnika;
	}
	
	public ArrayList<OcjenaPutopisa> getOcjenePutopisa() {
		return ocjenePutopisa;
	}

	public void setOcjenePutopisa(ArrayList<OcjenaPutopisa> ocjenePutopisa) {
		this.ocjenePutopisa = ocjenePutopisa;
	}
	
	public static void addOcjenaToOcjenaPutopisa(OcjenaPutopisa op)
	{
		ocjenePutopisa.add(op);
	}
	
	public static void addOcjenaToOcjenaKorisnika(OcjenaPutopisa op)
	{
		ocjeneKorisnika.add(op);
	}
	
	public static void getOcjeneKorisnika(Korisnik korisnik) throws SQLException
	{
		//ocjeneKorisnika = new ArrayList<OcjenaPutopisa>();
		ocjeneKorisnika = OcjenaPutopisaDAO.getGradesByUser(korisnik);
		for (OcjenaPutopisa op : ocjeneKorisnika)
		{
			System.out.println(op.getOcjena());
		}
	}
	
	public static void getOcjenePutopisa(Putopis putopis) throws SQLException
	{
		ocjenePutopisa = new ArrayList<OcjenaPutopisa>();
		ocjenePutopisa = OcjenaPutopisaDAO.getGradesByTravel(putopis);
	}
}
