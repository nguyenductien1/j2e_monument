package ico.ductien.proj.monument.entities;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@Table(name = "MonumentPhotos")
public class MonumentPhotos {
	@Id	
	@Column(length = 10)
	private String photoId;
	

	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="Monument", insertable=false ,updatable=false)
	private Monument Monument;
	
	@Column(length = 200)
	private String photoUri;
	
	public MonumentPhotos(Monument monument, String photoUri) {
		this.setMonument(monument);
		this.setPhotoUri(photoUri);
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public Monument getMonument() {
		return Monument;
	}

	public void setMonument(Monument monument) {
		Monument = monument;
	}

	public String getPhotoUri() {
		return photoUri;
	}

	public void setPhotoUri(String photoUri) {
		this.photoUri = photoUri;
	}
	
	
}
