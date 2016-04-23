package net.etfbl.beans;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import net.etfbl.Utility;
import net.etfbl.dto.Slika;

@ManagedBean(name = "slikaBean")
@SessionScoped
public class SlikaBean {
	private ArrayList<Slika> noveSlike;
	private ArrayList<Slika> userPhotos;
	private String nazivAlbuma;
	
	//private String putanjaDoSlike = Utility.setPutanjaDoProjekta() + "/WEB-INF/slike/testSlike.jpg";
	private StreamedContent stream;
	
	public SlikaBean()
	{
		try {
			stream = new DefaultStreamedContent(new FileInputStream(new File(Utility.setPutanjaDoProjekta() + "/WEB-INF/slike/testSlike.jpg")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Slika> getNoveSlike() {
		return noveSlike;
	}

	public void setNoveSlike(ArrayList<Slika> noveSlike) {
		this.noveSlike = noveSlike;
	}

	public ArrayList<Slika> getUserPhotos() {
		return userPhotos;
	}

	public void setUserPhotos(ArrayList<Slika> userPhotos) {
		this.userPhotos = userPhotos;
	}

	public String getNazivAlbuma() {
		return nazivAlbuma;
	}

	public void setNazivAlbuma(String nazivAlbuma) {
		this.nazivAlbuma = nazivAlbuma;
	}

	public String noveSlike() {
		JPanel component = new JPanel();
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(component);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			System.out.println(file.getName());
			File fileDst = new File(Utility.setPutanjaDoProjekta() + "/WEB-INF/slike/testSlike.jpg");
			InputStream input = null;
			OutputStream output = null;
			try {
				input = new FileInputStream(file);
				output = new FileOutputStream(fileDst);
				byte[] buf = new byte[1024];
				int bytesRead;
				while ((bytesRead = input.read(buf)) > 0) {
					output.write(buf, 0, bytesRead);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					input.close();
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return "userpage";
	}

	public StreamedContent getStream() {
		return stream;
	}

	public void setStream(StreamedContent stream) {
		this.stream = stream;
	}
}
