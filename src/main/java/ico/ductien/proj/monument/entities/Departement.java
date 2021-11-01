package ico.ductien.proj.monument.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@Table(name = "departement")
public class Departement implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	@Id	
	@Column(name = "dep", length = 4)
	private String dep;
	
	@Column(name = "nomDep", length = 30)
	private String nomDep;

	@OneToOne
	@JoinColumn(name="chefLieu", insertable=true ,updatable=true)
	private Lieu chefLieu;

    @Column(name = "reg", length = 46)
    private String reg;

	public Departement() {
		super();
	}
	
	public Departement(String dep) {
		super();
		this.setDep(dep);
	}

	
	public Departement(String dep, String nomDep, Lieu chefLieu, String reg) {
		super();
		this.setDep(dep);
		this.setNomDep(nomDep);
        this.setReg(reg);
		this.setChefLieu(chefLieu);

	}


	public String getDep() {
		return dep;
	}


	public void setDep(String dep) {
		this.dep = dep;
	}


	public String getNomDep() {
		return nomDep;
	}

    public void setReg(String reg) {
		this.reg = reg;
	}


	public String getReg() {
		return reg;
	}


	public void setNomDep(String nomDep) {
		this.nomDep = nomDep;
	}
	
	
	public Lieu getChefLieu() {
		return chefLieu;
	}

	public void setChefLieu(Lieu chefLieu) {
		this.chefLieu = chefLieu;
	}

	@JsonIgnore
	public Departement getDepartement() {
		return this;
	}
	
	@JsonSetter
	public void setDepartement(Departement departement) {
		departement.setDepartement(this);
	}


	
	@Override
    public String toString() {
        return "Numéro Departement: " + this.dep + ", Nom Departement: " + this.nomDep +
        		", Chef Lieu: " + this.chefLieu  ;
        					 
    }
	
}
