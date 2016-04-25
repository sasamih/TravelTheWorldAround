package net.etfbl.beans;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import net.etfbl.Utility;
import net.etfbl.dao.AlbumDAO;
import net.etfbl.dao.OcjenaSlikeDAO;
import net.etfbl.dao.PutopisDAO;
import net.etfbl.dao.SlikaDAO;
import net.etfbl.dto.Album;
import net.etfbl.dto.OcjenaSlike;
import net.etfbl.dto.Putopis;
import net.etfbl.dto.Slika;

@ManagedBean(name = "slikaBean")
@SessionScoped
public class SlikaBean {
	private ArrayList<Slika> noveSlike;
	private ArrayList<Slika> userPhotos;
	private ArrayList<Album> userAlbums;
	private List<Slika> albumPhotos;
	private String nazivAlbuma;
	private StreamedContent stream;
	private Album travelAlbum;
	
	private ArrayList<OcjenaSlike> ocjeneKorisnika = null;

	public SlikaBean() {
		nazivAlbuma = "";
		try {
			stream = new DefaultStreamedContent(new FileInputStream(new File(
					Utility.setPutanjaDoProjekta()
							+ "/WEB-INF/slike/testSlike.jpg")));
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

	public void prijava() throws SQLException
	{
		userAlbums = AlbumDAO.getByUser(Utility.prijavljeniKorisnik);
	}
	
	public String noveSlike() throws SQLException {
		Album album = sacuvajAlbum();
		JPanel component = new JPanel();
		final JFileChooser fc = new JFileChooser();
		fc.setMultiSelectionEnabled(true);
		int returnVal = fc.showOpenDialog(component);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File[] files = fc.getSelectedFiles();
			for (File file : files) {
				String putanjaSlike = "/WEB-INF/slike/" + file.getName();
				File fileDst = new File(Utility.setPutanjaDoProjekta()
						+ putanjaSlike);
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
				Slika slika = new Slika();
				slika.setAlbum(album);
				slika.setKorisnik(Utility.prijavljeniKorisnik);
				slika.setPutanjaSlike(putanjaSlike);
				slika.setStatusSlika(0);
				SlikaDAO.insert(slika);
			}
		}
		nazivAlbuma = null;
		
		return "newAlbum";
	}
	
	public void pronadjiAlbumPutopisa(Putopis putopis) throws SQLException
	{
		travelAlbum = AlbumDAO.getByTravel(putopis);
		System.out.println(travelAlbum.getNazivAlbuma());
	}
	
	public Album getTravelAlbum() {
		return travelAlbum;
	}

	public void setTravelAlbum(Album travelAlbum) {
		this.travelAlbum = travelAlbum;
	}

	public void setAlbumPhotos(List<Slika> albumPhotos) {
		this.albumPhotos = albumPhotos;
	}

	public String prikaziSlikeAlbuma(Album album) throws SQLException, FileNotFoundException
	{
		albumPhotos = SlikaDAO.getPhotosByAlbum(album);
		//System.out.println(album.getNazivAlbuma());
		ocjeneKorisnika = new ArrayList<OcjenaSlike>();
		for(Slika s : albumPhotos)
		{
			if (Utility.prijavljeniKorisnik != null)
			{
				OcjenaSlike ocjena = OcjenaSlikeDAO.getGradesByPhotoAndUser(s, Utility.prijavljeniKorisnik);
				if (ocjena != null)
				{
					ocjeneKorisnika.add(ocjena);
					System.out.println(s.getPutanjaSlike() + " " + ocjena.getOcjena());
				}
				else
				{
					OcjenaSlike ocjenaTmp = new OcjenaSlike();
					ocjenaTmp.setKorisnik(Utility.prijavljeniKorisnik);
					ocjenaTmp.setSlika(s);
					ocjenaTmp.setOcjena(0);
					ocjeneKorisnika.add(ocjenaTmp);
				}
			}
		}
		
		for (Slika slika : albumPhotos)
		{
//			File file = new File(Utility.setPutanjaDoProjekta() + "/WEB-INF/slike/testSlike.jpg");
//			if (file.exists())
//				slika.setStream(new DefaultStreamedContent(new FileInputStream(file)));
//			else
//				System.out.println(Utility.setPutanjaDoProjekta() + "/WEB-INF/slike/testSlike.jpg");
		}
		return "albumPhotos";
	}
	
	public void promjeniOcjenu(OcjenaSlike ocjena) throws SQLException
	{
		for(OcjenaSlike op : ocjeneKorisnika)
		{
			if (op.getSlika().getIdSlike() == ocjena.getSlika().getIdSlike())
			{
				if (OcjenaSlikeDAO.getById(ocjena) == null)
				{
					OcjenaSlikeDAO.insert(ocjena);
				}
				else
				{
					OcjenaSlikeDAO.update(ocjena);
				}
				break;
			}
		}
	}
	
	private Album sacuvajAlbum() throws SQLException
	{
		Album album = new Album();
		album.setKorisnik(Utility.prijavljeniKorisnik);
		album.setNazivAlbuma(nazivAlbuma);
		int id = AlbumDAO.insert(album);
		album.setIdAlbuma(id);
		
		return album;
	}
	
	public String sacuvajSlike() throws SQLException 
	{
		return "userpage";
	}

	public StreamedContent getStream() {
		return stream;
	}

	public void setStream(StreamedContent stream) {
		this.stream = stream;
	}

	public ArrayList<Album> getUserAlbums() {
		return userAlbums;
	}

	public void setUserAlbums(ArrayList<Album> userAlbums) {
		this.userAlbums = userAlbums;
	}

	public List<Slika> getAlbumPhotos() {
		return albumPhotos;
	}

	public void setAlbumPhotos(ArrayList<Slika> albumPhotos) {
		this.albumPhotos = albumPhotos;
	}

	public ArrayList<OcjenaSlike> getOcjeneKorisnika() {
		return ocjeneKorisnika;
	}

	public void setOcjeneKorisnika(ArrayList<OcjenaSlike> ocjeneKorisnika) {
		this.ocjeneKorisnika = ocjeneKorisnika;
	}
}
