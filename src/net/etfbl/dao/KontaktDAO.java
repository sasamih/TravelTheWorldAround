package net.etfbl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.etfbl.ConnectionPool;
import net.etfbl.dto.Kontakt;

import com.mysql.jdbc.PreparedStatement;

public class KontaktDAO {
	public static String gueryGetAllContacts = "select kontaktIme from KONTAKT where korisnikIme=?";
	public static String queryInsertContact = "insert into KONTAKT values(?, ?);";
	
	public static ArrayList<Kontakt> getAllContacts(String korisnickoIme) throws SQLException
	{
		ArrayList<Kontakt> kontakti = null;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(gueryGetAllContacts);
			ps.setString(1, korisnickoIme);
			ResultSet rs = ps.executeQuery();
			
			kontakti = new ArrayList<Kontakt>();
			
			while (rs.next())
			{
				Kontakt  kontakt = new Kontakt();
				kontakt.setKorisnikIme(korisnickoIme);
				kontakt.setKontaktIme(rs.getString(1));
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
			ps.setString(1, kontakt.getKorisnikIme());
			ps.setString(2, kontakt.getKontaktIme());
			ps.executeUpdate();
			success = true;
			ps.close();
		}
		conn.close();
		
		return success;
	}
}
