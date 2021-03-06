package net.etfbl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import net.etfbl.ConnectionPool;
import net.etfbl.dto.Korisnik;
import net.etfbl.dto.Putopis;

public class PutopisDAO {

	private static String queryInsert = "insert into PUTOPIS(`nazivPutopisa`, `datumObjavljivanja`, `podaciOMjestu`, `putanja`, `imeAutora`, `statusPutopis`, `prosjecnaOcjena`) values(?, ?, ?, ?, ?, ?, 0.00);";
	private static String queryUpdate = "update PUTOPIS set nazivPutopisa=?, datumObjavljivanja=?, podaciOMjestu=?, putanja=?, statusPutopis=0, prosjecnaOcjena=? where idPutopisa=?;";
	private static String queryUpdateStatus = "update PUTOPIS set statusPutopis=? where idPutopisa=?;";
	private static String queryUpdateProsjecnaOcjena = "update PUTOPIS set prosjecnaOcjena=? where idPutopisa=?;";
	private static String queryTravelsOnHold = "select * from PUTOPIS p inner join KORISNIK k on imeAutora=korisnickoIme where p.statusPutopis=0;";
	private static String queryTravelsByUser = "select * from PUTOPIS where imeAutora=?;";
	private static String queryCountByStatus = "select count(idPutopisa) from PUTOPIS where statusPutopis=?;";
	private static String queryGetAllTravels = "select * from PUTOPIS p inner join KORISNIK k on k.korisnickoIme=p.imeAutora;";
	
	private static String queryGetKeyWords = "select Tekst from KLJUCNE_RIJECI where PUTOPIS_idPutopis = ?;";
	
	public static ArrayList<Putopis> getByTravel(String tekst) {
		String queryGetByTravel = "SELECT p.idPutopisa, p.nazivPutopisa, p.putanja, p.prosjecnaOcjena from `traveldb`.`PUTOPIS` p inner join `traveldb`.`KLJUCNE_RIJECI` kr on kr.PUTOPIS_idPutopis=p.idPutopisa where kr.tekst like ? ";
		String[] tekstPretrage = tekst.split(" ");
		ArrayList<Putopis> putopisi = new ArrayList<Putopis>();
		for (int i = 1; i < tekstPretrage.length; i++) {
			queryGetByTravel += "or kr.tekst=? ";
		}
		queryGetByTravel += "and statusPutopis=1;";
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
				p.setProsjecnaOcjena(rs.getDouble("prosjecnaOcjena"));
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
	
	public static ArrayList<Putopis> getAllTravels() throws SQLException
	{
		ArrayList<Putopis> putopisi = null;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryGetAllTravels);
			ResultSet rs = ps.executeQuery();
			putopisi = new ArrayList<Putopis>();
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
				putopis.setProsjecnaOcjena(rs.getDouble(8));
				putopis.setBrojDjeljenja(rs.getInt(9));
				putopisi.add(putopis);
			}
			rs.close();
			ps.close();
		}
		conn.close();
		return putopisi;
	}

	public static int insert(Putopis putopis, String[] rijeci) throws SQLException
	{
		boolean success = false;
		int putopisId = 0;
		
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
			putopisId = rs.getInt(1);
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
		
		return putopisId;
	}
	
	public static boolean update(Putopis putopis, String[] rijeci) throws SQLException
	{
		boolean success = false;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryUpdate);
			ps.setString(1, putopis.getNazivPutopisa());
			ps.setString(2, putopis.getDatumObjavljivanja());
			ps.setString(3, putopis.getPodaciOMjestu());
			ps.setString(4, putopis.getPutanja());
			ps.setInt(5, putopis.getIdPutopisa());
			ps.setDouble(6, putopis.getProsjecnaOcjena());
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
	
	public static void updateProsjecnaOcjena(Putopis putopis) throws SQLException
	{
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryUpdateProsjecnaOcjena);
			ps.setDouble(1, putopis.getProsjecnaOcjena());
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
				putopis.setProsjecnaOcjena(rs.getDouble(8));
				putopisi.add(putopis);
			}
			rs.close();
			ps.close();
		}
		conn.close();
		
		return putopisi;
	}
	
	public static List<Putopis> getTravelsByUser(Korisnik korisnik) throws SQLException
	{
		List<Putopis> putopisi = new ArrayList<Putopis>();
		Connection conn = ConnectionPool.openConnection();
		
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryTravelsByUser);
			ps.setString(1, korisnik.getKorisnickoIme());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Putopis putopis = new Putopis();
				putopis.setIdPutopisa(rs.getInt(1));
				putopis.setNazivPutopisa(rs.getString(2));
				putopis.setDatumObjavljivanja(rs.getString(3));
				putopis.setPodaciOMjestu(rs.getString(4));
				putopis.setPutanja(rs.getString(5));
				putopis.setKorisnik(korisnik);
				putopis.setStatus(rs.getInt(7));
				putopis.setProsjecnaOcjena(rs.getDouble(8));
				putopisi.add(putopis);
			}
			rs.close();
			ps.close();
		}
		conn.close();
		
		return putopisi;
	}
	
	public static List<String> getKeyWords(int idPutopisa) throws SQLException
	{
		List<String> keyWords = null;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryGetKeyWords);
			ps.setInt(1, idPutopisa);
			ResultSet rs = ps.executeQuery();
			keyWords = new ArrayList<String>();
			
			while (rs.next())
			{
				keyWords.add(rs.getString(1));
			}
			rs.close();
			ps.close();
		}
		conn.close();
		
		return keyWords;
	}
	
	public static int getCount(int status) throws SQLException
	{
		int count = 0;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryCountByStatus);
			ps.setInt(1, status);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
			ps.close();
		}
		conn.close();
		return count;
	}
}
