package ico.ductien.proj.monument.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "Celebrite")
public class Celebrite implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name = "numCelebrite")
	private int numCelebrite;
	
	@Column(name = "nom", length = 16)
	private String nom;
	
	@Column(name = "prenom", length = 16)
	private String prenom;
	
	@Column(name = "nationalite", length = 10)
	private String nationalite;
	
	@Column(name = "epoque", length = 4)
	private String epoque;
	
	@Column(name="photoUrl")
	private String photoUrl;
	

	public Celebrite() {
		super();
	}



	public Celebrite(String nom, String prenom, String nationalite, String epoque) {
		super();
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setNationalite(nationalite);
		this.setEpoque(epoque);
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getNationalite() {
		return nationalite;
	}



	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}



	public String getEpoque() {
		return epoque;
	}



	public void setEpoque(String epoque) {
		this.epoque = epoque;
	}

	
	public Celebrite getCelebrite() {
		return this;
	}
	
	public String getPhotoUrl() {
		return photoUrl;
	}



	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	@Override
    public String toString() {
        return "Nom Celebrite: " + this.nom + ", Prénom Celebrite: " + this.prenom + ", Nationalite: " + this.nationalite +
        	   ", Epoque: " + this.epoque ;
    }
	
	
}
