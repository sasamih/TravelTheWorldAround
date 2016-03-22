package net.etfbl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.etfbl.dto.Korisnik;

public class KorisnikDAO {

	public static boolean prijavljen(Korisnik korisnik) throws ClassNotFoundException, SQLException
	{
		boolean prijavljen = false;
		
		Connection conn = ConnectorClass.openConnection();
		
		if (conn != null)
		{
			String query = "select * from korisnik where korisnickoIme=? and lozinka=?;";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, korisnik.getKorisnickoIme());
			ps.setString(2, korisnik.getLozinka());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next())
			{
				prijavljen = true;
			}
			
			rs.close();
			ps.close();
			conn.close();
		}
		
		return prijavljen;
	}
	
	public static boolean insert(Korisnik korisnik) throws ClassNotFoundException, SQLException
	{
		boolean insertKorisnik = false;
		
		Connection conn = ConnectorClass.openConnection();
		
		if (conn != null)
		{
			String query = "insert into korisnik values(?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, korisnik.getIme());
			ps.setString(2, korisnik.getPrezime());
			ps.setString(3, korisnik.getEmail());
			ps.setString(4, korisnik.getBiografija());
			ps.setString(5, korisnik.getDatumRodjenja());
			ps.setInt(6, Integer.parseInt(korisnik.getKorisnickaGrupa()));
			ps.setString(7, korisnik.getKorisnickoIme());
			ps.setString(8, korisnik.getLozinka());
			
			ps.executeUpdate();
			insertKorisnik = true;
		}
		
		return insertKorisnik;
	}
}
