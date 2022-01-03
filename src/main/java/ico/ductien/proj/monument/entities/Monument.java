package ico.ductien.proj.monument.entities;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.Set;
@Entity
@Table(name = "Monument")
public class Monument implements Serializable{
   
	private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "codeM", length = 12)
    private String codeM;

    @Column(name = "nomM", length = 80)
    private String nomM;

    @Column(length = 10)
	private String proprietaire;

    @Column(name = "typeMonument", length = 20)
    private String typeMonument;

    @Column(name = "longitude")
	private double longitude;
	
	@Column(name = "latitude")
	private double latitude;
	
	@Column(name = "photoUrl")
	private String photoUrl;

	
    @ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="codeLieu", insertable= true ,updatable=true)
	private Lieu lieu;
	
	

    @ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name= "associeA", joinColumns=@JoinColumn(name="codeM"), inverseJoinColumns=@JoinColumn(name="numCelebrite"))
    @JsonManagedReference
	private Set<Celebrite> celebrities;

	public Monument() {
		super();
	}
	
	public Monument(String codeM) {
		super();
		this.setCodeM(codeM);
	}

	                                                           

	public Monument(String codeM, String nomM, String proprietaire, String typeMonument, double longitude,
			double latitude, String codeLieu) {
		super();
		this.setCodeM(codeM);
		this.setNomM(nomM);
		this.setProprietaire(proprietaire);
		this.setTypeMonument(typeMonument);
		this.setLongitude(longitude);
		this.setLatitude(latitude);
		
		
	}
	
	

	public String getCodeM() {
		return codeM;
	}


	public void setCodeM(String codeM) {
		this.codeM = codeM;
	}


	public String getNomM() {
		return nomM;
	}


	public void setNomM(String nomM) {
		this.nomM = nomM;
	}


	public String getProprietaire() {
		return proprietaire;
	}


	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}


	public String getTypeMonument() {
		return typeMonument;
	}


	public void setTypeMonument(String typeMonument) {
		this.typeMonument = typeMonument;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public String getPhotoUrl() {
		return photoUrl;
	}



	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	@JsonIgnore
	public Monument getMonument() {
		return this;
	}
	
	

	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}


	public Set<Celebrite> getCelebrities() {
		return celebrities;
	}

	public void setCelebrities(Set<Celebrite> celebrities) {
		this.celebrities = celebrities;
	}

	@Override
    public String toString() {
        return "Code Monument: " + this.codeM + ", Nom Monument: " + this.nomM + ", Proprietaire: " + this.proprietaire +
        	   ", Type Monument: " + this.typeMonument + ", Longitude: " + this.longitude + ", Latitude: " + this.latitude	+
        	   ", Localisation: " + this.lieu ;
    }
    
    
}
