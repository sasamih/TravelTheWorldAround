package net.etfbl;

import javax.faces.context.FacesContext;

public class Utility {

	public static String projectPath = "";
	
	public static String setPutanjaDoProjekta()
	{
		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
		//String projectPath = "";
		
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
