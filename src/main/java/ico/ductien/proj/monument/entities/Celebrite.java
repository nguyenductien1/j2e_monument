package ico.ductien.proj.monument.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


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
	
	@ManyToMany(mappedBy="celebrities", fetch = FetchType.EAGER)
	@JsonBackReference
	private  Set<Monument> monuments = new HashSet<Monument>();
	

	public Celebrite() {
		super();
	}

		
	public Celebrite(int numCelebrite, String nom, String prenom, String nationalite, String epoque, String photoUrl) {
		super();
		this.setNumCelebrite(numCelebrite);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setNationalite(nationalite);
		this.setEpoque(epoque);
		this.setPhotoUrl(photoUrl);
	}



	public int getNumCelebrite() {
		return numCelebrite;
	}



	public void setNumCelebrite(int numCelebrite) {
		this.numCelebrite = numCelebrite;
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

	@JsonIgnore
	public Celebrite getCelebrite() {
		return this;
	}
	
	public String getPhotoUrl() {
		return photoUrl;
	}



	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	
	
	/**
	 * @return the monuments
	 */
	public Set<Monument> getMonuments() {
		return monuments;
	}

	/**
	 * @param monuments the monuments to set
	 */
	public void setMonuments(Set<Monument> monuments) {
		this.monuments = monuments;
    }
	
	@Override
    public String toString() {
        return "Nom Celebrite: " + this.nom + ", Prénom Celebrite: " + this.prenom + ", Nationalite: " + this.nationalite +
        	   ", Epoque: " + this.epoque ;
    }
	
	
}
