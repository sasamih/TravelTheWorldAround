package net.etfbl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import net.etfbl.ConnectionPool;
import net.etfbl.dto.Korisnik;
import net.etfbl.dto.OcjenaPutopisa;
import net.etfbl.dto.Putopis;

public class OcjenaPutopisaDAO {
	
	private static String queryInsert = "insert into OCJENA_PUTOPIS(`PUTOPIS_idPutopis`, `korisnickoIme`, `ocjena`) values(?, ?, ?);";
	private static String queryGetGradesByUser = "select * from OCJENA_PUTOPIS op inner join PUTOPIS p on op.korisnickoIme=p.imeAutora where op.korisnickoIme=?;";
	private static String queryGetGradesByTravel = "select * from OCJENA_PUTOPIS op inner join KORISNIK k on op.korisnickoIme=k.korisnickoIme where op.PUTOPIS_idPutopis=?;";
	
	public static boolean insert(OcjenaPutopisa ocjena) throws SQLException
	{
		boolean success = false;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryInsert);
			ps.setInt(1, ocjena.getPutopis().getIdPutopisa());
			ps.setString(2, ocjena.getKorisnik().getKorisnickoIme());
			ps.setInt(3, ocjena.getOcjena());
			ps.executeUpdate();
			success = true;
			ps.close();
		}
		conn.close();
		
		return success;
	}
	
	public static ArrayList<OcjenaPutopisa> getGradesByUser(Korisnik korisnik) throws SQLException
	{
		ArrayList<OcjenaPutopisa> ocjene = null;
		Connection conn = ConnectionPool.openConnection();
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryGetGradesByUser);
			ps.setString(1, korisnik.getKorisnickoIme());
			ResultSet rs = ps.executeQuery();
			
			ocjene = new ArrayList<OcjenaPutopisa>();
			while (rs.next())
			{
				OcjenaPutopisa ocjena = new OcjenaPutopisa();
				ocjena.setIdOcjene(rs.getInt("idOcjene"));
				ocjena.setKorisnik(korisnik);
				ocjena.setOcjena(rs.getInt("ocjena"));
				Putopis putopis = new Putopis();
				putopis.setIdPutopisa(rs.getInt("idPutopisa"));
				putopis.setNazivPutopisa(rs.getString("nazivPutopisa"));
				putopis.setDatumObjavljivanja(rs.getString("datumObjavljivanja"));
				putopis.setPodaciOMjestu(rs.getString("podaciOMjestu"));
				putopis.setPutanja(rs.getString("putanja"));
				putopis.setKorisnik(korisnik);
				putopis.setStatus(rs.getInt("statusPutopis"));
				putopis.setProsjecnaOcjena(rs.getDouble("prosjecnaOcjena"));
				ocjena.setPutopis(putopis);
				ocjene.add(ocjena);
			}
			rs.close();
			ps.close();
		}
		conn.close();
		return ocjene;
	}
	
	public static ArrayList<OcjenaPutopisa> getGradesByTravel(Putopis putopis) throws SQLException
	{
		ArrayList<OcjenaPutopisa> ocjene = null;
		Connection conn = ConnectionPool.openConnection();
		
		if (conn != null)
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryGetGradesByTravel);
			ps.setInt(1, putopis.getIdPutopisa());
			ResultSet rs = ps.executeQuery();
			ocjene = new ArrayList<OcjenaPutopisa>();
			
			while(rs.next())
			{
				OcjenaPutopisa ocjena = new OcjenaPutopisa();
				ocjena.setIdOcjene(rs.getInt("idOcjene"));
				ocjena.setPutopis(putopis);
				ocjena.setOcjena(rs.getInt("ocjena"));
				ocjena.setKorisnik(KorisnikDAO.setUser(rs));
				ocjene.add(ocjena);
			}
			rs.close();
			ps.close();
		}
		conn.close();
		return ocjene;
	}
}