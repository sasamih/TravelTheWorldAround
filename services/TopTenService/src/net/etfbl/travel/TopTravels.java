package net.etfbl.travel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class TopTravels {

	public ArrayList<Putopis> getTopTenTravels()
	{
		ArrayList<Putopis> putopisi = null;
		Connection conn = openConnection();
		try
		{
			final String query = "select nazivPutopisa, putanja, prosjecnaOcjenas from PUTOPIS;";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return putopisi;
	}
	
	private Connection openConnection()
	{
		Connection conn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/traveldb", "root", "root");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}
}
