package net.etfbl.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import net.etfbl.ConnectionPool;
import net.etfbl.Utility;
import net.etfbl.dto.Korisnik;

public class KorisnikDAO {
		private static String queryGetAll = "select * from knjiga";
		private static String queryGetByUsernameAndPassowrd = "select * from KORISNIK where korisnickoIme like ? and lozinka like ?;";
		private static String queryGetByName = "select * from KORISNIK where korisnickoIme like ?;";
		private static String queryUpdateStatus = "UPDATE KORISNIK SET `status`=? WHERE `korisnickoIme`=?;";
		private static String queryInsert = "INSERT INTO `traveldb`.`KORISNIK` VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		private static String queryExists = "select * from KORISNIK where korisnickoIme=?;";
		private static String queryOnHold = "select * from KORISNIK where status=0;";
		
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
		
		public static boolean insertKorisnik(Korisnik korisnik) throws NoSuchAlgorithmException
		{
			Connection conn = ConnectionPool.openConnection();
			try {
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryInsert);
				ps.setString(1, korisnik.getIme());
				ps.setString(2, korisnik.getKorisnickoIme());
				ps.setString(3, getMD5Hash(korisnik.getLozinka()));
				ps.setString(4, korisnik.getPrezime());
				ps.setString(5, korisnik.geteMail());
				ps.setString(6, korisnik.getKratkaBiografija());
				ps.setString(7, korisnik.getDatumRodjenja());
				ps.setString(8, korisnik.getKorisnickaGrupa());
				ps.setInt(9, 0);
				
				ps.executeUpdate();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
		
		public static Korisnik login(String korisnickoIme, String lozinka) throws NoSuchAlgorithmException, SQLException
		{
			String hashLozinka = getMD5Hash(lozinka);
			Korisnik korisnik = null;
			
			Connection conn = ConnectionPool.openConnection();
			if (conn != null)
			{
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryGetByUsernameAndPassowrd);
				ps.setString(1, korisnickoIme);
				ps.setString(2, hashLozinka);
				ResultSet rs = ps.executeQuery();
				if (rs.next())
				{
					if (rs.getInt(9) != 0)
					{
						korisnik = new Korisnik();
						korisnik = setUser(rs);
						
						if (korisnik.getStatus() == 2)
						{
							changeStatus(conn, 1, korisnickoIme);
						}
					}
				}
				
				rs.close();
				ps.close();
			}
			conn.close();
			return korisnik;
			
		}
		
		public static void deactivate(Korisnik korisnik) throws SQLException
		{
			Connection conn = ConnectionPool.openConnection();
			if (conn != null)
			{
				changeStatus(conn, 2, korisnik.getKorisnickoIme());
			}
			conn.close();
		}
		
		public static void activate(Korisnik korisnik) throws SQLException
		{
			Connection conn = ConnectionPool.openConnection();
			if (conn != null)
			{
				changeStatus(conn, 1, korisnik.getKorisnickoIme());
			}
			conn.close();
		}
		
		private static void changeStatus(Connection conn, int status, String korisnickoIme) throws SQLException
		{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryUpdateStatus);
			ps.setInt(1, status);
			ps.setString(2, korisnickoIme);
			ps.executeUpdate();
			ps.close();
		}
		
		public static boolean exists(String korisnickoIme) throws SQLException
		{
			Connection conn = ConnectionPool.openConnection();
			
			if (conn != null)
			{
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryExists);
				ps.setString(1, korisnickoIme);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
					return true;
			}
			return false;
		}
		
		public static ArrayList<Korisnik> getUsersOnHold() throws SQLException
		{
			ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
			
			Connection conn = ConnectionPool.openConnection();
			if (conn != null)
			{
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(queryOnHold);
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					Korisnik korisnik = new Korisnik();
					korisnik = setUser(rs);
					korisnici.add(korisnik);
				}
				rs.close();
				ps.close();
			}
			conn.close();
			
			return korisnici;
		}
		
		private static Korisnik setUser(ResultSet rs) throws SQLException
		{
			Korisnik korisnik = new Korisnik();
			korisnik.setIme(rs.getString(1));
			korisnik.setKorisnickoIme(rs.getString(2));
			korisnik.setLozinka(rs.getString(3));
			korisnik.setPrezime(rs.getString(4));
			korisnik.seteMail(rs.getString(5));
			korisnik.setKratkaBiografija(rs.getString(6));
			korisnik.setDatumRodjenja(rs.getString(7));
			korisnik.setKorisnickaGrupa(rs.getString(8));
			korisnik.setStatus(rs.getInt(9));
			
			return korisnik;
		}
		
		private static String getMD5Hash(String lozinka) throws NoSuchAlgorithmException
		{
			String hash = "";
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(lozinka.getBytes());
			byte[] digest = md.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			hash = bigInt.toString(16);
			while(hash.length() < 32)
			{
				hash = "0" + hash;
			}
			
			return hash;
		}

}
