package net.etfbl.travel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.jws.WebService;

import com.mysql.jdbc.PreparedStatement;

@WebService
public class TopTravels {

	public Putopis[] getTopTenTravels()
	{
		List<Putopis> putopisi = null;
		Connection conn = openConnection();
		try
		{
			final String query = "select idPutopisa, nazivPutopisa, putanja, prosjecnaOcjena from PUTOPIS;";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			putopisi = new ArrayList<Putopis>();
			
			while(rs.next())
			{
				Putopis putopis = new Putopis();
				putopis.setIdPutopisa(rs.getInt("idPutopisa"));
				putopis.setNazivPutopisa(rs.getString("nazivPutopisa"));
				putopis.setPutanja(rs.getString("putanja"));
				putopis.setProsjecnaOcjena(rs.getDouble("prosjecnaOcjena"));
				putopisi.add(putopis);
			}
			rs.close();
			ps.close();
			
			Collections.sort(putopisi, new Comparator<Putopis>(){
				public int compare(Putopis p1, Putopis p2)
				{
					return Double.compare(p2.getProsjecnaOcjena(), p1.getProsjecnaOcjena());
				}
			});
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		Putopis[] nizPutopisa = null;
		
		if (putopisi != null)
		{
			if (putopisi.size() >= 10)
				nizPutopisa = new Putopis[10];
			else
				nizPutopisa = new Putopis[putopisi.size()];
			int duzina = nizPutopisa.length;
			for (int i=0; i < ((duzina < 11)?duzina:10); i++)
			{
				nizPutopisa[i] = putopisi.get(i);
			}
		}
	
		return nizPutopisa;
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/traveldb", "root", "rtrk");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}
}
