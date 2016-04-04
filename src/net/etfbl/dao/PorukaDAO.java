package net.etfbl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import net.etfbl.ConnectionPool;
import net.etfbl.dto.Poruka;

public class PorukaDAO {

	public static String gueryAllMessagesForContact = "select tekstPoruke, statusProcitana, vrijemeSlanja from PORUKA where posiljalac=? and primalac=?;";
	
	public static ArrayList<Poruka> getAllMessagesForContact(String posiljalac, String primalac) throws SQLException
	{
		ArrayList<Poruka> poruke = null;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(gueryAllMessagesForContact);
			ps.setString(1, posiljalac);
			ps.setString(2, primalac);
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
			ps.setString(2, posiljalac);
			ps.setString(1, primalac);
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
