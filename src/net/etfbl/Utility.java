package net.etfbl;

import java.sql.SQLException;

import javax.faces.context.FacesContext;

import net.etfbl.beans.KorisnikBean;
import net.etfbl.dto.Korisnik;
import net.etfbl.dto.Putopis;

public class Utility {

	public static String projectPath = "";
	public static Korisnik prijavljeniKorisnik = null;
	public static Korisnik primalacPoruke = null;
	public static Putopis noviPutopis = null;
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
