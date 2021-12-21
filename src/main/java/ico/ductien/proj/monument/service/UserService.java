package ico.ductien.proj.monument.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ico.ductien.proj.monument.entities.User;
import ico.ductien.proj.monument.repository.UserRepository;

import java.io.UnsupportedEncodingException;
import java.security.*;


@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public static List<User> listUser = new ArrayList<User>();
	  
	  
	  public List<User> findAll() {
		    return userRepository.findAll();
		  }
	  
	  public Optional<User> findById(int id) {
	    return userRepository.findById(id);
	  }
	  
	  public boolean add(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
	    if (userRepository.getUser(user.getUsername()) !=null) {
	    	return false;
	    }
	    user.setPassword(hashPw(user.getPassword()));
	    user.setRoles(new String[] { "ROLE_USER" });
	    userRepository.save(user);
	    return true;
	  }
	  
	  public boolean addAdmin(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		    if (userRepository.getUser(user.getUsername()) !=null) {
		    	return false;
		    }
		    user.setPassword(hashPw(user.getPassword()));
		    user.setRoles(new String[] { "ROLE_ADMIN" });
		    userRepository.save(user);
		    return true;
		  }
	  public void delete(int id) {
	    userRepository.deleteById(id);
	  }
	 //Check login
	  public boolean checkLogin(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		List<User> users = userRepository.findAll();	
	    for (User userExist : users) {
	      if (StringUtils.equals(user.getUsername(), userExist.getUsername())) {
	    	  if (hashPw(user.getPassword()).equals(userExist.getPassword())) {
	    		  return true;
	    	  }
	        return false;
	      }
	    }
	    return false;
	  }

	public User getUser(String username) {
		
		return userRepository.getUser(username);
	}
	
	public String hashPw(String pw) throws UnsupportedEncodingException, NoSuchAlgorithmException {
	
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		String hex = (new HexBinaryAdapter()).marshal(md5.digest(pw.getBytes()));		
		return hex;	
		
	}
	
	
}
