package ico.ductien.proj.monument.entities;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = { "roles", "authorities" })
@Entity
@Table(name = "User")
public class User {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", length = 12)
	private int id;
	@Column(name = "username", length = 20)
	private String username;
	@Column(name = "password", length = 256)
	private String password;
	@Column(name = "roles", length = 20)
	private String[] roles;
	
	public User() {
	  }
	  public User(int id, String username, String password) {
	    this.id = id;
	    this.username = username;
	    this.password = password;
	  }
	  public List<GrantedAuthority> getAuthorities() {
	    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    for (String role : roles) {
	      authorities.add(new SimpleGrantedAuthority(role));
	    }
	    return authorities;
	  }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String[] getRoles() {
		return roles;
	}
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	  
	

}
