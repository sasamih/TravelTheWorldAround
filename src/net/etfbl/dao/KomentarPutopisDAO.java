package net.etfbl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import net.etfbl.ConnectionPool;
import net.etfbl.dto.KomentarPutopisa;
import net.etfbl.dto.Putopis;

public class KomentarPutopisDAO {

	private static String queryInsert = "insert into KOMENTAR_PUTOPIS values(`imaAutora`, `PUTOPIS_idPutopis`, `tekstKomentara`) values(?, ?, ?);";
	private static String queryUpdate = "update KOMENTAR_PUTOPIS set tekstKomentara=? where idKomentara=?;";
	private static String queryGetCommentsByTravel = "select * from KOMENTAR_PUTOPIS kp inner join KORISNIK k on k.korisnickoIme=kp.imeAutora where kp.PUTOPIS_idPutopis=?;";
	
	public static boolean insert(KomentarPutopisa komentar) throws SQLException
	{
		boolean success = false;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryInsert);
			ps.setString(1, komentar.getKorisnik().getKorisnickoIme());
			ps.setInt(2, komentar.getPutopis().getIdPutopisa());
			ps.setString(3, komentar.getTekstKomentara());
			ps.executeUpdate();
			success = true;
			ps.close();
		}
		conn.close();
		
		return success;
	}
	
	public static boolean update(KomentarPutopisa komentar) throws SQLException
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
	
	public static ArrayList<KomentarPutopisa> getCommentsByTravel(Putopis putopis) throws SQLException
	{
		ArrayList<KomentarPutopisa> komentari = null;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryGetCommentsByTravel);
			ps.setInt(1, putopis.getIdPutopisa());
			System.out.println(putopis.getIdPutopisa());
			ResultSet rs = ps.executeQuery();
			komentari = new ArrayList<KomentarPutopisa>();
			while (rs.next())
			{
				KomentarPutopisa komentar = new KomentarPutopisa();
				komentar.setIdKomentara(rs.getInt(1));
				komentar.setTekstKomentara(rs.getString(4));
				komentar.setKorisnik(KorisnikDAO.setUser(rs));
				komentar.setPutopis(putopis);
				komentari.add(komentar);
			}
			rs.close();
			ps.close();
		}
		conn.close();
		return komentari;
	}
}
