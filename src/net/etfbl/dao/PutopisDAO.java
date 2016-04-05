package net.etfbl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import net.etfbl.ConnectionPool;
import net.etfbl.dto.Korisnik;
import net.etfbl.dto.Putopis;

public class PutopisDAO {

	public static String queryInsert = "insert into PUTOPIS(`nazivPutopisa`, `datumObjavljivanja`, `podaciOMjestu`, `putanja`, `imeAutora`, `statusPutopis`) values(?, ?, ?, ?, ?, ?);";
	public static String queryUpdateStatus = "update PUTOPIS set statusPutopis=? where idPutopisa=?;";
	public static String queryTravelsOnHold = "select * from PUTOPIS p inner join KORISNIK k on imeAutora=korisnickoIme where p.statusPutopis=0;";
	
	public static ArrayList<Putopis> getByTravel(String tekst) {
		String queryGetByTravel = "SELECT p.idPutopisa, p.nazivPutopisa, p.putanja from `traveldb`.`PUTOPIS` p inner join `traveldb`.`KLJUCNE_RIJECI` kr on kr.PUTOPIS_idPutopis=p.idPutopisa where kr.tekst like ? ";
		String[] tekstPretrage = tekst.split(" ");
		ArrayList<Putopis> putopisi = new ArrayList<Putopis>();
		for (int i = 1; i < tekstPretrage.length; i++) {
			queryGetByTravel += "or kr.tekst=? ";
		}
		queryGetByTravel += ";";
		Connection conn = ConnectionPool.openConnection();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryGetByTravel);
			;
			for (int i = 0; i < tekstPretrage.length; i++) {
				ps.setString(i + 1, "%" + tekstPretrage[i] + "%");
			}
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				boolean ima = false;
				Putopis p = new Putopis();
				p.setIdPutopisa(rs.getInt("idPutopisa"));
				p.setPutanja(rs.getString("putanja"));
				p.setNazivPutopisa(rs.getString("nazivPutopisa"));
				for (Putopis pp : putopisi) {
					if (pp.getIdPutopisa() == p.getIdPutopisa())
						ima = true;
				}
				if (!ima)
					putopisi.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return putopisi;
	}

	public static boolean insert(Putopis putopis, String[] rijeci) throws SQLException
	{
		boolean success = false;
		
		Connection conn = ConnectionPool.openConnection();
		
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryInsert);
			ps.setString(1, putopis.getNazivPutopisa());
			ps.setString(2, putopis.getDatumObjavljivanja());
			ps.setString(3, putopis.getPodaciOMjestu());
			ps.setString(4, putopis.getPutanja());
			ps.setString(5, putopis.getKorisnik().getKorisnickoIme());
			ps.setInt(6, 0);
			ps.executeUpdate();
			
			String lastIdQuery = "select last_insert_id();";
			ps = (PreparedStatement) conn.prepareStatement(lastIdQuery);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int putopisId = rs.getInt(1);
			rs.close();
			
			String keyWordQuery = "insert into KLJUCNE_RIJECI(`Tekst`, `PUTOPIS_idPutopis`) values(?, ?);";
			for (int i = 0; i < rijeci.length; i++)
			{
				ps = (PreparedStatement) conn.prepareStatement(keyWordQuery);
				ps.setString(1, rijeci[i]);
				ps.setInt(2, putopisId);
				ps.executeUpdate();
			}
			
			ps = (PreparedStatement) conn.prepareStatement(keyWordQuery);
			ps.setString(1, putopis.getNazivPutopisa());
			ps.setInt(2, putopisId);
			ps.executeUpdate();
			
			success = true;
			ps.close();
		}
		conn.close();
		
		return success;
	}
	

	public static void updateStatus(Putopis putopis) throws SQLException
	{
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryUpdateStatus);
			ps.setInt(1, putopis.getStatus());
			ps.setInt(2, putopis.getIdPutopisa());
			ps.executeUpdate();
			ps.close();
		}
		conn.close();
	}
	
	public static ArrayList<Putopis> getTravelsOnHold() throws SQLException
	{
		ArrayList<Putopis> putopisi = new ArrayList<Putopis>();
		
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryTravelsOnHold);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Putopis putopis = new Putopis();
				putopis.setIdPutopisa(rs.getInt(1));
				putopis.setNazivPutopisa(rs.getString(2));
				putopis.setDatumObjavljivanja(rs.getString(3));
				putopis.setPodaciOMjestu(rs.getString(4));
				putopis.setPutanja(rs.getString(5));
				putopis.setKorisnik(KorisnikDAO.setUser(rs));
				putopis.setStatus(rs.getInt(7));
				putopisi.add(putopis);
			}
			rs.close();
			ps.close();
		}
		conn.close();
		
		return putopisi;
	}
}
