package net.etfbl.beans;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;

import net.etfbl.dao.KorisnikDAO;
import net.etfbl.dto.Korisnik;

@ManagedBean(name = "korisnikBean")
public class KorisnikBean {
	private Korisnik korisnik = new Korisnik();

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	public void insert()
	{
		try {
			KorisnikDAO.insert(korisnik);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
