package fi.agileo.tietokonejpa;

// JPA:n vaatimat kirjastojen importtaukset
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;

@Stateless
public class TietokoneJpa {
	// http://stackoverflow.com/questions/21038706/persistenceunit-vs-persistencecontext
	@PersistenceContext(unitName = "jsf_jpa_tietokone")
	private EntityManager em;

	public TietokoneJpa() {
	}

	public void tallennaTietokone(Tietokone tietokone) {
		try {
			em.persist(tietokone);
			System.out.println("persist tietokone:" + tietokone);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Tallennus ei onnistunut!");
		}
	}
	
	public void alustaTietokoneet() {
		Tietokone tk1 = new Tietokone();
		tk1.setMerkki("HP");
		tk1.setMerkki("Elitebook");
		Tietokone tk2 = new Tietokone();
		tk2.setMerkki("Apple");
		tk2.setMerkki("MacBook");
		em.persist(tk1);
		em.persist(tk2);
	}
	
	public List<Tietokone> haeTietokoneet() {
		List<Tietokone> haetut = null; 
		haetut = em.createNamedQuery("haeKaikki").getResultList();
		System.out.println("***haeKaikki***");
		return haetut;
	}


}
