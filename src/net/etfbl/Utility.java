package net.etfbl;

import java.sql.SQLException;

import javax.faces.context.FacesContext;

import net.etfbl.beans.KorisnikBean;
import net.etfbl.dto.Korisnik;

public class Utility {

	public static String projectPath = "";
	public static Korisnik prijavljeniKorisnik = new Korisnik();
	public static String lozinka = "";
	public static boolean newTravel;
	
	public static String setPutanjaDoProjekta()
	{
		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
		projectPath = "";
		
		String[] folderNames = path.split("/");
		for (String f : folderNames)
		{
			if(!(f.equals(".metadata")))
			{
				projectPath += "/" + f;
			}
			else
				break;
		}
		projectPath += "/TravelTheWorldAround/WebContent";
		
		return projectPath;
	}
}
