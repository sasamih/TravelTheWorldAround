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

	public static ArrayList<Putopis> getByTravel(String tekst) {
		String queryGetByTravel = "SELECT p.idPutopisa, p.nazivPutopisa, p.tekstPutopisa from `traveldb`.`PUTOPIS` p inner join `traveldb`.`KLJUCNE_RIJECI` kr on kr.PUTOPIS_idPutopis=p.idPutopisa where kr.tekst=? ";
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
				ps.setString(i + 1, tekstPretrage[i]);
			}
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				boolean ima = false;
				Putopis p = new Putopis();
				p.setIdPutopisa(rs.getInt("idPutopisa"));
				p.setTekstPutopisa(rs.getString("tekstPutopisa"));
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

	/*
	 * public static boolean insertKorisnik(Korisnik korisnik) { Connection conn
	 * = ConnectionPool.openConnection(); try { PreparedStatement ps =
	 * (PreparedStatement) conn.prepareStatement(querryInsert); ps.setString(1,
	 * korisnik.getIme()); ps.setString(2, korisnik.getKorisnickoIme());
	 * ps.setString(3, korisnik.getLozinka()); ps.setString(4,
	 * korisnik.getPrezime()); ps.setString(5, korisnik.geteMail());
	 * ps.setString(6, korisnik.getKratkaBiografija()); ps.setString(7,
	 * korisnik.getDatumRodjenja()); ps.setString(8,
	 * korisnik.getKorisnickaGrupa());
	 * 
	 * ps.executeUpdate(); ps.close(); conn.close(); } catch (SQLException e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); } return true; }
	 */

}
