package fi.agileo.tietokonejpa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
// JPA:n vaatimat kirjastojen importtaukset
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;

@ManagedBean
public class TietokoneController {
	// olio sisältää datan tallennuksen ja haun tietokannasta JPA:lla
	@EJB
	private TietokoneJpa tkJpa;

	@ManagedProperty(value = "#{tietokone}")
	private Tietokone tietokone;

	public TietokoneController() {
		//tkJpa.alustaTietokoneet();

	}

	public Tietokone getTietokone() {
		return tietokone;
	}

	public void setTietokone(Tietokone tietokone) {
		this.tietokone = tietokone;
	}

	public String tallennaTietokone() {
		String palautus = "Tallennus onnistui: " + tietokone;

		FacesContext facesContext = FacesContext.getCurrentInstance();
		// JSF:ssa luodun beanin nimellä päästään olioon kiinni "fish"
		// (faces-config.xml)
		Tietokone tk = (Tietokone) facesContext.getExternalContext().getRequestMap().get("tietokone");
		System.out.println("tk:" + tk);
		// System.out.println("tietokone:" + tietokone);

		tkJpa.tallennaTietokone(tk);
		// tkJpa.tallennaTietokone(tietokone);

		return palautus;
	}

	public List<Tietokone> getTietokoneet() {
		return tkJpa.haeTietokoneet();
	}

	public String alustaTietokoneet() {
		tkJpa.alustaTietokoneet();
		return "index";
	}
}
