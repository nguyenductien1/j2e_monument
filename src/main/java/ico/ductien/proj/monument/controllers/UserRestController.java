package ico.ductien.proj.monument.controllers;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ico.ductien.proj.monument.entities.User;
import ico.ductien.proj.monument.service.UserService;
import ico.ductien.proj.monument.service.JwtService;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/")
public class UserRestController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;

	/* ---------------- GET ALL USER ------------------------ */
	@RequestMapping(value = "/api/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUser() {
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}

	/* ---------------- GET USER BY ID ------------------------ */
	@RequestMapping(value = "/api/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getUserById(@PathVariable int id) {
		Optional<User> user = userService.findById(id);
		if (user != null) {
			return new ResponseEntity<Object>(user, HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Not Found User", HttpStatus.NO_CONTENT);
	}

	/* ---------------- CREATE NEW USER ------------------------ */
	@RequestMapping(value = "/api/users", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		if (userService.add(user)) {
			return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("User Existed!", HttpStatus.BAD_REQUEST);
		}
	}
	
	/* ---------------- CREATE NEW ADMIN ------------------------ */
	@RequestMapping(value = "/api/users/admin", method = RequestMethod.POST)
	public ResponseEntity<String> createUserAdmin(@RequestBody User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		if (userService.addAdmin(user)) {
			return new ResponseEntity<String>("Created Admin!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("User Admin Existed!", HttpStatus.BAD_REQUEST);
		}
	}

	/* ---------------- DELETE USER ------------------------ */
	@RequestMapping(value = "/api/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUserById(@PathVariable int id) {
		userService.delete(id);
		return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
	}
	
	/* ---------------- LOGIN ------------------------ */
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public ResponseEntity<HashMap> login(HttpServletRequest request, @RequestBody User user) {
		String result = "";
		HttpStatus httpStatus = null;
		String userName = null;
		String roles =null;
		User userDetails;
		HashMap <String, String> resultat = new HashMap<String, String>();
		 
		try {
			if (userService.checkLogin(user)) {
				result = jwtService.generateTokenLogin(user.getUsername());
				userName = jwtService.getUsernameFromToken(result);
				userDetails = userService.getUser(userName);
				roles = userDetails.getAuthorities().toString();
				httpStatus = HttpStatus.OK;
				resultat.put("userName", userName);
				resultat.put("token", result);
				resultat.put("roles", roles);
			} else {
				result = "Wrong userID and password";
				resultat.put("error", result);
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception ex) {
			result = "Server Error";
			resultat.put("error", result);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<HashMap>(resultat, httpStatus);
	}
	
}
