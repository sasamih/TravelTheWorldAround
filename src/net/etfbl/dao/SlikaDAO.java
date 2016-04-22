package net.etfbl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import net.etfbl.ConnectionPool;
import net.etfbl.dto.Korisnik;
import net.etfbl.dto.Slika;

public class SlikaDAO {
	public static String queryInsert = "insert into SLIKA(`putanjaSlike`, `imeAutora`, `statusSlika`) values(?, ?, ?);";
	public static String queryGetByUser = "select * from SLIKA where imeAutora=?;";
	
	public boolean insert(Slika slika) throws SQLException
	{
		boolean success = false;
		Connection conn = ConnectionPool.openConnection();
		
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryInsert);
			ps.setString(1, slika.getPutanjaSlike());
			ps.setString(2, slika.getKorisnik().getKorisnickoIme());
			ps.setInt(3, slika.getStatusSlika());
			ps.executeUpdate();
			success = true;
			ps.close();
		}
		conn.close();
		return success;
	}
	
	public ArrayList<Slika> getPhotosByUser(Korisnik korisnik) throws SQLException
	{
		ArrayList<Slika> slike = null;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryGetByUser);
			ps.setString(1, korisnik.getKorisnickoIme());
			ResultSet rs = ps.executeQuery();
			slike = new ArrayList<Slika>();
			
			while(rs.next())
			{
				Slika slika = new Slika();
				slika.setIdSlike(rs.getInt(1));
				slika.setPutanjaSlike(rs.getString(2));
				slika.setKorisnik(korisnik);
				slika.setStatusSlika(rs.getInt(4));
				slike.add(slika);
			}
		}
		return slike;
	}
}
