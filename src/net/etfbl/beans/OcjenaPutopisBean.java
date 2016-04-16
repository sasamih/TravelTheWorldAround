package net.etfbl.beans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.etfbl.dto.OcjenaPutopisa;

@ManagedBean(name = "ocjenaPutopisBean")
@SessionScoped
public class OcjenaPutopisBean {

	private ArrayList<OcjenaPutopisa>  ocjeneKorisnika;
}
