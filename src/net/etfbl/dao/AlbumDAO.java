package net.etfbl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.etfbl.ConnectionPool;
import net.etfbl.dto.Album;
import net.etfbl.dto.Korisnik;

public class AlbumDAO {
	
	public static String queryInsert = "insert into ALBUM(`imeAutora`,`nazivAlbuma`) values(?, ?);";
	public static String queryUpdate = "update ALBUM set nazivAlbuma=? where idAlbuma=?;";
	public static String queryGetByUser = "select * from ALBUM where imeAutora=?;";
	
	public static int insert(Album album) throws SQLException
	{
		//boolean success = false;
		int albumId = 0;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = conn.prepareStatement(queryInsert);
			ps.setString(1, album.getKorisnik().getKorisnickoIme());
			ps.setString(2, album.getNazivAlbuma());
			ps.executeUpdate();
			//success = true;
			String lastIdQuery = "select last_insert_id();";
			ps = (PreparedStatement) conn.prepareStatement(lastIdQuery);
			ResultSet rs = ps.executeQuery();
			rs.next();
			albumId = rs.getInt(1);
			rs.close();
			ps.close();
		}
		conn.close();
		return albumId;
	}
	
	public static boolean update(Album album) throws SQLException
	{
		boolean success = false;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = conn.prepareStatement(queryUpdate);
			ps.setString(1, album.getNazivAlbuma());
			ps.setInt(2, album.getIdAlbuma());
			ps.executeUpdate();
			success = true;
			ps.close();
		}
		conn.close();
		return success;
	}
	
	public static ArrayList<Album> getByUser(Korisnik korisnik) throws SQLException
	{
		ArrayList<Album> albumi = null;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = conn.prepareStatement(queryGetByUser);
			ps.setString(1, korisnik.getKorisnickoIme());
			ResultSet rs = ps.executeQuery();
			albumi = new ArrayList<Album>();
			while(rs.next())
			{
				Album album = new Album();
				album.setIdAlbuma(rs.getInt(1));
				album.setKorisnik(korisnik);
				album.setNazivAlbuma(rs.getString(3));
				albumi.add(album);
			}
		}
		conn.close();
		return albumi;
	}
}
