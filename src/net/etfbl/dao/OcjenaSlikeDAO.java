package net.etfbl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.etfbl.ConnectionPool;
import net.etfbl.dto.Korisnik;
import net.etfbl.dto.OcjenaSlike;
import net.etfbl.dto.Putopis;
import net.etfbl.dto.Slika;

import com.mysql.jdbc.PreparedStatement;

public class OcjenaSlikeDAO {

	private static String queryInsert = "insert into OCJENA_SLIKA(`SLIKA_idSlike`, `korisnickoIme`, `ocjena`) values(?, ?, ?);";
	private static String queryUpdate = "update OCJENA_SLIKA set ocjena=? where idOcjene=?;";
	private static String queryGetById = "select * from OCJENA_SLIKA where idOcjene=?;";
	private static String queryGetGradesByPhotoAndUser = "select * from OCJENA_SLIKA os inner join KORISNIK k on os.korisnickoIme=k.korisnickoIme inner join SLIKA s on s.idSlike=os.SLIKA_idSlike where os.korisnickoIme=? and s.idSlike=?;";

	public static boolean insert(OcjenaSlike ocjena) throws SQLException
	{
		boolean success = false;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryInsert);
			ps.setInt(1, ocjena.getSlika().getIdSlike());
			ps.setString(2, ocjena.getKorisnik().getKorisnickoIme());
			ps.setInt(3, ocjena.getOcjena());
			ps.executeUpdate();
			success = true;
			ps.close();
		}
		conn.close();
		
		return success;
	}
	
	public static boolean update(OcjenaSlike ocjena) throws SQLException
	{
		boolean success = false;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryUpdate);
			ps.setInt(1, ocjena.getOcjena());
			ps.setInt(2, ocjena.getIdOcjene());
			ps.executeUpdate();
			success = true;
		}
		
		return success;
	}
	
	public static OcjenaSlike getById(OcjenaSlike ocjena) throws SQLException
	{
		OcjenaSlike ocjenaTmp = null;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryGetById);
			ps.setInt(1, ocjena.getIdOcjene());
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				ocjenaTmp = new OcjenaSlike();
			}
		}
		return ocjenaTmp;
	}
	
	public static OcjenaSlike getGradesByPhotoAndUser(Slika slika, Korisnik korisnik) throws SQLException
	{
		OcjenaSlike ocjena = null;
		Connection conn = ConnectionPool.openConnection();
		
		if(conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryGetGradesByPhotoAndUser);
			ps.setString(1, korisnik.getKorisnickoIme());
			ps.setInt(2, slika.getIdSlike());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next())
			{
				ocjena = new OcjenaSlike();
				ocjena.setIdOcjene(rs.getInt(1));
				ocjena.setKorisnik(korisnik);
				ocjena.setSlika(slika);
				ocjena.setOcjena(rs.getInt(3));
			}
			rs.close();
			ps.close();
		}
		conn.close();
		return ocjena;
	}
}
