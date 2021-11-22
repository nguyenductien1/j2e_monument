package ico.ductien.proj.monument.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ico.ductien.proj.monument.entities.User;
import ico.ductien.proj.monument.repository.UserRepository;
@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public static List<User> listUser = new ArrayList<User>();
	  static {
	    User userKai = new User(1, "dtn", "123456");
	    userKai.setRoles(new String[] { "ROLE_ADMIN" });
	    listUser.add(userKai);
	  }
	  
	  public List<User> findAll() {
		    return userRepository.findAll();
		  }
	  
	  public Optional<User> findById(int id) {
	    return userRepository.findById(id);
	  }
	  
	  public boolean add(User user) {
	    if (userRepository.getUser(user.getUsername()) !=null) {
	    	return false;
	    }
	    user.setPassword(hashPw(user.getPassword()));
	    user.setRoles(new String[] { "ROLE_USER" });
	    userRepository.save(user);
	    return true;
	  }
	  public void delete(int id) {
	    userRepository.deleteById(id);
	  }
	 //Errors
	  public boolean checkLogin(User user) {
		List<User> users = userRepository.findAll();
		Pbkdf2PasswordEncoder pwc = pwc ();		
	    for (User userExist : users) {
	      if (StringUtils.equals(user.getUsername(), userExist.getUsername())) {
	    	  if (pwc.matches(user.getPassword(), userExist.getPassword())) {
	    		  return true;
	    	  }
	        return false;
	      }
	    }
	    return false;
	  }

	public User getUser(String username) {
		// TODO Auto-generated method stub
		return userRepository.getUser(username);
	}
	
	public String hashPw(String pw) {
		String keyEncode = "dtn1122";
		int iterations = 200;
		int hashWidth = 256;
		Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder(keyEncode, iterations, hashWidth);
		encoder.setEncodeHashAsBase64(true);
		String encodedPw = encoder.encode(pw);
		
		return encodedPw;	
		
	}
	public Pbkdf2PasswordEncoder pwc () {
		String keyEncode = "dtn1122";
		int iterations = 200;
		int hashWidth = 256;
		Pbkdf2PasswordEncoder pw = new Pbkdf2PasswordEncoder(keyEncode, iterations, hashWidth);
		return pw;	
	}
}
