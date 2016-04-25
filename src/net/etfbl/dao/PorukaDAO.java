package net.etfbl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import net.etfbl.ConnectionPool;
import net.etfbl.dto.Korisnik;
import net.etfbl.dto.Poruka;

public class PorukaDAO {

	private static String queryInsert = "insert into PORUKA(`posiljalac`, `primalac`, `tekstPoruke`, `statusProcitana`, `vrijemeSlanja`) values(?, ?, ?, ?, ?);";
	private static String queryUpdate = "update PORUKA set statusProcitana=1 where idPoruke=?;";
	private static String gueryAllMessagesForContact = "select tekstPoruke, statusProcitana, vrijemeSlanja from PORUKA where posiljalac=? and primalac=?;";
	private static String queryGetAllUnreadMessages = "select * from PORUKA p inner join KORISNIK k on p.posiljalac=k.korisnickoIme where p.primalac=? and p.statusProcitana=0;";
	
	public static boolean insert(Poruka poruka) throws SQLException
	{
		boolean success = false;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryInsert);
			ps.setString(1, poruka.getPosiljalac().getKorisnickoIme());
			ps.setString(2, poruka.getPrimalac().getKorisnickoIme());
			ps.setString(3, poruka.getTekstPoruke());
			ps.setInt(4, 0);
			ps.setString(5, poruka.getVrijemeSlanja());
			ps.executeUpdate();
			success = true;
			ps.close();
		}
		conn.close();
		return success;
	}
	
	public static boolean update(Poruka poruka) throws SQLException
	{
		boolean success = false;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryUpdate);
			ps.setInt(1, poruka.getIdPoruke());
			ps.executeUpdate();
			success = true;
			ps.close();
		}
		conn.close();
		return success;
	}
	
	public static ArrayList<Poruka> getAllUnreadMessages(Korisnik primalac) throws SQLException
	{
		ArrayList<Poruka> poruke = null;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryGetAllUnreadMessages);
			ps.setString(1, primalac.getKorisnickoIme());
			ResultSet rs = ps.executeQuery();
			poruke = new ArrayList<Poruka>();
			
			while (rs.next())
			{
				Poruka poruka = new Poruka();
				poruka.setIdPoruke(rs.getInt(1));
				poruka.setPosiljalac(KorisnikDAO.setUser(rs));
				poruka.setPrimalac(primalac);
				poruka.setTekstPoruke(rs.getString(4));
				poruka.setStatusProcitana(rs.getInt(5));
				poruka.setVrijemeSlanja(rs.getString(6));
				poruke.add(poruka);
			}
			rs.close();
			ps.close();
		}
		conn.close();
		return poruke;
	}
	
	public static ArrayList<Poruka> getAllMessagesForContact(Korisnik posiljalac, Korisnik primalac) throws SQLException
	{
		ArrayList<Poruka> poruke = null;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(gueryAllMessagesForContact);
			ps.setString(1, posiljalac.getKorisnickoIme());
			ps.setString(2, primalac.getKorisnickoIme());
			ResultSet rs = ps.executeQuery();
			poruke = new ArrayList<Poruka>();
			
			while (rs.next())
			{
				Poruka poruka = new Poruka();
				poruka.setPosiljalac(posiljalac);
				poruka.setPrimalac(primalac);
				poruka.setTekstPoruke(rs.getString(1));
				poruka.setStatusProcitana(rs.getInt(2));
				poruka.setVrijemeSlanja(rs.getString(3));
				poruke.add(poruka);
			}
			rs.close();
			ps.close();
			
			
			// think about making this as two methods , one helper just to call this one
			ps = (PreparedStatement) conn.prepareStatement(gueryAllMessagesForContact);
			ps.setString(2, posiljalac.getKorisnickoIme());
			ps.setString(1, primalac.getKorisnickoIme());
			rs = ps.executeQuery();
			
			while (rs.next())
			{
				Poruka poruka = new Poruka();
				poruka.setPosiljalac(posiljalac);
				poruka.setPrimalac(primalac);
				poruka.setTekstPoruke(rs.getString(1));
				poruka.setStatusProcitana(rs.getInt(2));
				poruka.setVrijemeSlanja(rs.getString(3));
				poruke.add(poruka);
			}
		}
		
		return poruke;
	}
}
