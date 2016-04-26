package net.etfbl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import net.etfbl.ConnectionPool;
import net.etfbl.dto.KomentarSlike;
import net.etfbl.dto.Slika;

public class KomentarSlikeDAO {
	private static String queryInsert = "insert into KOMENTAR_SLIKA (`imeAutora`, `SLIKA_idSlike`, `tekstKomentara`) values(?, ?, ?);";
	private static String queryUpdate = "update KOMENTAR_SLIKA set tekstKomentara=? where idKomentara=?;";
	private static String queryGetCommentsByPhoto = "select * from KOMENTAR_SLIKA ks inner join KORISNIK k on k.korisnickoIme=ks.imeAutora where ks.SLIKA_idSlike=?;";
	
	public static boolean insert(KomentarSlike komentar) throws SQLException
	{
		boolean success = false;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryInsert);
			ps.setString(1, komentar.getKorisnik().getKorisnickoIme());
			ps.setInt(2, komentar.getSlika().getIdSlike());
			ps.setString(3, komentar.getTekstKomentara());
			ps.executeUpdate();
			success = true;
			ps.close();
		}
		conn.close();
		
		return success;
	}
	
	public static boolean update(KomentarSlike komentar) throws SQLException
	{
		boolean success = false;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryUpdate);
			ps.setString(1, komentar.getTekstKomentara());
			ps.setInt(2, komentar.getIdKomentara());
			ps.executeUpdate();
			success = true;
			ps.close();
		}	
		conn.close();
		return success;
	}
	
	public static ArrayList<KomentarSlike> getCommentsByTravel(Slika slika) throws SQLException
	{
		ArrayList<KomentarSlike> komentari = null;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryGetCommentsByPhoto);
			ps.setInt(1, slika.getIdSlike());
			ResultSet rs = ps.executeQuery();
			komentari = new ArrayList<KomentarSlike>();
			while (rs.next())
			{
				KomentarSlike komentar = new KomentarSlike();
				komentar.setIdKomentara(rs.getInt(1));
				komentar.setTekstKomentara(rs.getString(4));
				komentar.setKorisnik(KorisnikDAO.setUser(rs));
				komentar.setSlika(slika);
				komentari.add(komentar);
			}
			rs.close();
			ps.close();
		}
		conn.close();
		return komentari;
	}
}
