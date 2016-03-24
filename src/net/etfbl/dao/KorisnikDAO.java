package net.etfbl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import net.etfbl.ConnectionPool;
import net.etfbl.dto.Korisnik;

public class KorisnikDAO {
		private static String queryGetAll = "select * from knjiga";
		private static String queryGetByName = "select * from KORISNIK where korisnickoIme like ?;";
		private static String queryUpadate = "UPDATE `knjigadb`.`knjiga` SET `status`=? WHERE `id`=?;";
		private static String queryInsert = "INSERT INTO `traveldb`.`KORISNIK` VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		
		
		public static ArrayList<Korisnik> getByName(String naziv)
		{
			ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
			Connection conn = ConnectionPool.openConnection();
			try {
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryGetByName);
				ps.setString(1, "%" + naziv + "%");
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					korisnici.add(new Korisnik(rs.getString("ime"), rs.getString("korisnickoIme"), rs.getString("lozinka"), rs.getString("prezime"), rs.getString("eMail"), rs.getString("kratkaBiografija"), rs.getString("datumRodjenja"), rs.getString("korisnickaGrupa")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return korisnici;
		}
		
		public static boolean insertKorisnik(Korisnik korisnik)
		{
			Connection conn = ConnectionPool.openConnection();
			try {
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryInsert);
				ps.setString(1, korisnik.getIme());
				ps.setString(2, korisnik.getKorisnickoIme());
				ps.setString(3, korisnik.getLozinka());
				ps.setString(4, korisnik.getPrezime());
				ps.setString(5, korisnik.geteMail());
				ps.setString(6, korisnik.getKratkaBiografija());
				ps.setString(7, korisnik.getDatumRodjenja());
				ps.setString(8, korisnik.getKorisnickaGrupa());
				
				ps.executeUpdate();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}


}
