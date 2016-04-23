package net.etfbl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import net.etfbl.ConnectionPool;
import net.etfbl.dto.Album;
import net.etfbl.dto.Korisnik;
import net.etfbl.dto.Slika;

public class SlikaDAO {
	public static String queryInsert = "insert into SLIKA(`putanjaSlike`, `imeAutora`, `statusSlika`,`idAlbuma`) values(?, ?, ?, ?);";
	public static String queryGetByUser = "select * from SLIKA where imeAutora=?;";
	public static String queryGetByAlbum = "select * from SLIKA s inner join KORISNIK k on k.korisnickoIme=s.imeAutora where idAlbuma=?;";
	
	public static boolean insert(Slika slika) throws SQLException
	{
		boolean success = false;
		Connection conn = ConnectionPool.openConnection();
		
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryInsert);
			ps.setString(1, slika.getPutanjaSlike());
			ps.setString(2, slika.getKorisnik().getKorisnickoIme());
			ps.setInt(3, slika.getStatusSlika());
			ps.setInt(4, slika.getAlbum().getIdAlbuma());
			ps.executeUpdate();
			success = true;
			ps.close();
		}
		conn.close();
		return success;
	}
	
	public static ArrayList<Slika> getPhotosByUser(Korisnik korisnik) throws SQLException
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
	
	public static ArrayList<Slika> getPhotosByAlbum(Album album) throws SQLException
	{
		ArrayList<Slika> slike = null;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryGetByUser);
			ps.setInt(1, album.getIdAlbuma());
			ResultSet rs = ps.executeQuery();
			slike = new ArrayList<Slika>();
			
			while(rs.next())
			{
				Slika slika = new Slika();
				slika.setIdSlike(rs.getInt(1));
				slika.setPutanjaSlike(rs.getString(4));
				slika.setAlbum(album);
				slika.setStatusSlika(rs.getInt(5));
				slika.setKorisnik(KorisnikDAO.setUser(rs));
				slike.add(slika);
			}
		}
		return slike;
	}
}
