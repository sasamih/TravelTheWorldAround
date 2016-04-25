package net.etfbl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.etfbl.ConnectionPool;
import net.etfbl.dto.Kontakt;
import net.etfbl.dto.Korisnik;

import com.mysql.jdbc.PreparedStatement;

public class KontaktDAO {
	public static String gueryGetAllContacts = "select kontaktIme from KONTAKT where korisnikIme=?";
	public static String queryInsertContact = "insert into KONTAKT values(?, ?);";
	
	public static ArrayList<Kontakt> getAllContacts(Korisnik korisnik) throws SQLException
	{
		ArrayList<Kontakt> kontakti = null;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(gueryGetAllContacts);
			ps.setString(1, korisnik.getKorisnickoIme());
			ResultSet rs = ps.executeQuery();
			
			kontakti = new ArrayList<Kontakt>();
			
			while (rs.next())
			{
				Kontakt  kontakt = new Kontakt();
				kontakt.setKorisnik(korisnik);
				kontakt.setKontakt(KorisnikDAO.getSingleByName(rs.getString(1)));
				kontakti.add(kontakt);
			}
			rs.close();
			ps.close();
		}
		conn.close();
		
		return kontakti;
	}
	
	public static boolean insert(Kontakt kontakt) throws SQLException
	{
		boolean success = false;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryInsertContact);
			ps.setString(1, kontakt.getKorisnik().getKorisnickoIme());
			ps.setString(2, kontakt.getKontakt().getKorisnickoIme());
			ps.executeUpdate();
			success = true;
			ps.close();
		}
		conn.close();
		
		return success;
	}
}
