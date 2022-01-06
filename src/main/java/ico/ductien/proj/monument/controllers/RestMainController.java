package ico.ductien.proj.monument.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.ui.Model;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.Set;
import java.util.UUID;

import ico.ductien.proj.monument.service.*;
import ico.ductien.proj.monument.entities.*;
import ico.ductien.proj.monument.repository.DepartementRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class RestMainController {

	@Autowired
    private MonumentService monumentService;
	@Autowired
	private LieuService lieuService;
	@Autowired
	private DepartementService departementService;
	@Autowired
	private CelebriteService celebriteService;
	
	//-------------------------------DEPARTEMENT----------------------------------------//
    /* Rajouter un département */
	@RequestMapping(value="/api/departements", method=RequestMethod.POST, headers="Accept=application/json")
    public Departement dep (@RequestBody String departement) {
		
		JSONObject depJSON = new JSONObject(departement);
		String nomDep = depJSON.getString("nomDep");
		String chefLieu = depJSON.getJSONObject("chefLieu").getString("codeInsee");
		String dep = depJSON.getString("dep");
		String reg = depJSON.getString("reg");
		Lieu chef = lieuService.getLieu(chefLieu);
		Departement dept = new Departement(dep, nomDep, chef, reg);
        return departementService.addDepartement(dept);
       
	}
	/* Update un département */
	@RequestMapping(value="/api/departements/{numDep}", method=RequestMethod.PATCH, headers="Accept=application/json")
	public Departement updateDepartement (@RequestBody String updateData, @PathVariable String numDep) {
		
		JSONObject depJSON = new JSONObject(updateData);
		String codeInseeChefLieu = depJSON.getString("codeInseeChefLieu");
        return departementService.updateDepartement(codeInseeChefLieu, numDep);
       
	}
	/*Delete un département*/
	@RequestMapping(value="/api/departements/{numDep}", method=RequestMethod.DELETE, headers="Accept=application/json")
	public ResponseEntity<String> deleteDepartement (@PathVariable String numDep) {
		
		Departement departement = departementService.getDepartement(numDep);
        departementService.deleteDepartement(departement);
		return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
       
	}
	
	/*Details un département*/
	@RequestMapping(value="/api/departements/{numDep}", method=RequestMethod.GET, headers="Accept=application/json")
	public ResponseEntity<Departement> getDetailsDepartement (@PathVariable String numDep) {
		
		Departement departement = departementService.getDepartement(numDep);
		return new ResponseEntity<>(departement, HttpStatus.OK);
       
	}
	/*Get all lieux by département*/
	@RequestMapping(value="/api/departements/lieux/{numDep}", method=RequestMethod.GET, headers="Accept=application/json")
	public ResponseEntity<List<Lieu>> getLieuxByDepartement (@PathVariable String numDep) {
        
		List<Lieu> lieux = departementService.findLieuxByCodeDep(numDep);
		return new ResponseEntity<List<Lieu>>(lieux, HttpStatus.OK);
       
	}
	
	
	/*Méthode pour prendre tous les département*/
    @RequestMapping(value="/api/departements", method=RequestMethod.GET)
	public List<Departement> getAllDepartements() {
		return departementService.getListAllDepartements();
	}
  //---------------------------------LIEUX--------------------------------------------//
    /*Méthode pour rajouter un lieu*/
    @RequestMapping(value="/api/lieux", method=RequestMethod.POST)
    public Lieu addLieu(@RequestBody Lieu lieu){
        return lieuService.addLieu(lieu);
    }
    /*Méthode pour get details d'un lieu*/
    @RequestMapping(value="/api/lieux/{codeInsee}", method=RequestMethod.GET)
    public  ResponseEntity<Lieu> getDetailsLieu(@PathVariable String codeInsee){
    	Lieu lieu = lieuService.getLieu(codeInsee);
        return new ResponseEntity<Lieu>(lieu, HttpStatus.OK);
    }
    /*Méthode pour modifier un lieu*/
    @RequestMapping(value="/api/lieux/{id}", method=RequestMethod.PATCH)
    public Lieu editLieu(@RequestBody Lieu lieu,  @PathVariable String id){
    	Lieu lieuMod = lieuService.getLieu(id);
    	lieuMod.setCodeInsee(id);
    	lieuMod.setDep(lieu.getDep());
    	lieuMod.setNomCom(lieu.getNomCom());
    	lieuMod.setLatitude(lieu.getLatitude());
    	lieuMod.setLongitude(lieu.getLongitude());
        return lieuService.addLieu(lieuMod);
    }
    
    /*Méthode pour supprimer un lieu*/
    @RequestMapping(value="/api/lieux/{id}", method=RequestMethod.DELETE)
    public  ResponseEntity<String> deleteLieu(@PathVariable String id){
    	Lieu lieu = lieuService.getLieu(id);
    	lieu.setDep(null);
    	lieuService.addLieu(lieu);
        lieuService.deleteLieu(lieu);
        
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }
    
    /*Méthode pour prendre tous les lieux*/
    @RequestMapping(value="/api/lieux", method=RequestMethod.GET)
	public List<Lieu> getAllLieux() {
		return lieuService.getListAllLieux();
	}
    
    /*Méthode pour get all monument by Lieu*/
    @RequestMapping(value="/api/lieux/monuments/{codeInsee}", method=RequestMethod.GET)
    public  ResponseEntity<List<Monument>> getMonumentsbyLieux(@PathVariable String codeInsee){
    	
        List<Monument> monuments = lieuService.getMonumentsbyLieu(codeInsee);
        
        return new ResponseEntity<>(monuments, HttpStatus.ACCEPTED);
    }
    //---------------------------------MONUMENTS--------------------------------------------//
    
    /*Methode pour rajouter un monument*/
	@RequestMapping(value="/api/monuments", method=RequestMethod.POST)
	public ResponseEntity<String> addMonument(@RequestBody String monument){
		JSONObject monumentJson = new JSONObject(monument);
		Monument newMonument = new Monument();
		newMonument.setCodeM(monumentJson.getString("codeM"));
		newMonument.setNomM(monumentJson.getString("nomM"));
		if (monumentJson.getString("codeLieu") !=null) {
			Lieu lieu = lieuService.getLieu(monumentJson.getString("codeLieu"));
			newMonument.setLieu(lieu);
		}
		newMonument.setLongitude(monumentJson.getFloat("longitude"));
		newMonument.setLatitude(monumentJson.getFloat("latitude"));
		newMonument.setPhotoUrl(monumentJson.getString("photoUrl"));
		newMonument.setProprietaire(monumentJson.getString("proprietaire"));
		newMonument.setTypeMonument(monumentJson.getString("typeMonument"));
		
		
		monumentService.addMonument(newMonument);
		
		return new ResponseEntity<String>("CREATED", HttpStatus.CREATED);
	}
	
	/*Methode pour modifier un monument*/
	@RequestMapping(value="/api/monuments/{codeM}", method=RequestMethod.PATCH)
	public ResponseEntity<String> editMonument(@RequestBody String monumentString, @PathVariable String codeM){
		Monument monument = monumentService.findByCode(codeM);
		JSONObject monumentJson = new JSONObject(monumentString);
		monument.setNomM(monumentJson.getString("nomM"));
		if (monumentJson.getString("codeLieu") !=null) {
			Lieu lieu = lieuService.getLieu(monumentJson.getString("codeLieu"));
			monument.setLieu(lieu);
		}
		monument.setLongitude(monumentJson.getFloat("longitude"));
		monument.setLatitude(monumentJson.getFloat("latitude"));
		monument.setPhotoUrl(monumentJson.getString("photoUrl"));
		monument.setProprietaire(monumentJson.getString("proprietaire"));
		monument.setTypeMonument(monumentJson.getString("typeMonument"));
		
		monumentService.addMonument(monument);
		
		return new ResponseEntity<String>("Modified", HttpStatus.ACCEPTED);
	}
	
	/*Methode pour prendre tous les monuments*/
	@RequestMapping(value="/api/monuments", method=RequestMethod.GET)
	public ResponseEntity<List<Monument>> getAllMonuments() {
		
		List<Monument> monuments = monumentService.getListAllMonuments();
		return new ResponseEntity<>(monuments, HttpStatus.OK);
	}
	
	/*Methode return les information d'un monument*/
	@RequestMapping(value="/api/monuments/{codeM}", method=RequestMethod.GET)
	public ResponseEntity<Monument> getMonumentbyCode(@PathVariable String codeM){
		
		Monument monument = monumentService.findByCode(codeM);
		return new ResponseEntity<Monument>(monument,HttpStatus.OK);
		
	}
	
	/*Methode delete d'un monument*/
	@RequestMapping(value="/api/monuments/{codeM}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteMonumentbyCode(@PathVariable String codeM){
		
		Monument monument = monumentService.findByCode(codeM);
		monument.setLieu(null);
		monumentService.deleteMonument(monument);
		
		return new ResponseEntity<String>("DELETED",HttpStatus.ACCEPTED);
		
	}
	
	/*Méthode pour ajouter un celebrite dans un monument*/
	@RequestMapping(value="/api/monuments/celebrites", method=RequestMethod.POST )
	public ResponseEntity<String> addCelebriteToMonument(@RequestBody String monu_celeb){
		
		JSONObject monuCelebJSON = new JSONObject(monu_celeb);
		Monument monument = monumentService.findByCode(monuCelebJSON.getString("codeM"));
		Celebrite celebrite = celebriteService.findCelebirteByNumber(monuCelebJSON.getInt("numCelebrite"));
		Set<Celebrite> setCelebrite = monument.getCelebrities();
		setCelebrite.add(celebrite);
		monument.setCelebrities(setCelebrite);
		//monumentService.saveMonument(setCelebrite, monuCelebJSON.getString("codeM"));
		monumentService.saveMonumentV2(monument);
		
		return new ResponseEntity<String>("Added", HttpStatus.ACCEPTED);
	}
	
	/*Méthode pour delete un celebrite dans un monument*/
	@RequestMapping(value="/api/monuments/celebrites/{codeM}/{numCelebrite}", method=RequestMethod.DELETE )
	public ResponseEntity<String> deleteCelebriteFromMonument(@PathVariable String codeM, @PathVariable String numCelebrite){
		
		Monument monument = monumentService.findByCode(codeM);
		Celebrite celebrite = celebriteService.findCelebirteByNumber(Integer.parseInt(numCelebrite));
		Set<Celebrite> setCelebrite = monument.getCelebrities();
		setCelebrite.remove(celebrite);
		monument.setCelebrities(setCelebrite);
		monumentService.saveMonumentV2(monument);
		
		return new ResponseEntity<String>("Deleted", HttpStatus.ACCEPTED);
	}
	
	
	
	//----------------------------------CELEBRITES----------------------------------------------//
	/*Méthode pour prendre tous les célébrites*/
	@RequestMapping(value="/api/celebrites", method=RequestMethod.GET)
	public ResponseEntity<List<Celebrite>> getAllCelebrites() {
		
		List<Celebrite> celebrites = celebriteService.getAllCelebrite();
		return new ResponseEntity<>(celebrites, HttpStatus.OK);
	}
	
	/*Méthode pour rajouter un célébrite*/
	@RequestMapping(value="/api/celebrites", method=RequestMethod.POST)
	public ResponseEntity<String> addCelebrite(@RequestBody String celebrite){
		
		JSONObject celebJSON = new JSONObject(celebrite);
		Celebrite celeb = new Celebrite();
		celeb.setNom(celebJSON.getString("nom"));
		celeb.setPrenom(celebJSON.getString("prenom"));
		celeb.setNationalite(celebJSON.getString("nationalite"));
		celeb.setEpoque(celebJSON.getString("epoque"));
		celeb.setPhotoUrl(celebJSON.getString("photoUrl"));
		celebriteService.addCelebrite(celeb);
		
		
		return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
		
	}
	/*Méthode pour récupéer un célébrite*/
	@RequestMapping(value="/api/celebrites/{numCelebrite}", method=RequestMethod.GET)
	public ResponseEntity<Celebrite> getDetailCelebrite(@PathVariable int numCelebrite){
		
		Celebrite celebrite = celebriteService.findCelebirteByNumber(numCelebrite);
		
		return new ResponseEntity<Celebrite>(celebrite, HttpStatus.OK);
	}
	
	/*Méthode pour monuments dans un célébrite*/
	@RequestMapping(value="/api/celebrites/monuments/{numCelebrite}", method=RequestMethod.GET)
	public ResponseEntity<Set<Monument>> getMonumentsByCelebrite(@PathVariable int numCelebrite){
		
		Celebrite celebrite = celebriteService.findCelebirteByNumber(numCelebrite);
		Set<Monument> monuments = celebrite.getMonuments();
		
		return new ResponseEntity<Set<Monument>>(monuments, HttpStatus.OK);
	}
	
	/*Méthode pour modifier un célébrite*/
	@RequestMapping(value="/api/celebrites/{numCelebrite}", method=RequestMethod.PATCH)
	public ResponseEntity<String> editCelebrite(@RequestBody String celebrite, @PathVariable int numCelebrite){
		JSONObject celebJSON = new JSONObject(celebrite);
		
		celebriteService.setInfoCelebrite(celebJSON.getString("nom"), celebJSON.getString("prenom"), celebJSON.getString("photoUrl"), celebJSON.getString("nationalite"), celebJSON.getString("epoque"), numCelebrite);
		
		return new ResponseEntity<String>("Modified", HttpStatus.ACCEPTED);
	}
	
	
	/*Méthode pour delete un célébrite*/
	@RequestMapping(value="/api/celebrites/{numCelebrite}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteCelebrite(@PathVariable int numCelebrite){
		
		Celebrite celebrite = celebriteService.findCelebirteByNumber(numCelebrite);
		celebrite.setMonuments(null);
		celebriteService.deleteCelebrite(celebrite);
		
		return new ResponseEntity<String>("deleted", HttpStatus.ACCEPTED);
	}
	
	/*-----------------------------------POUR UPLOAD PHOTO------------------------------------------------------------*/
	/*Méthode pour upload photos*/
	@RequestMapping(value="/api/upload", method=RequestMethod.POST)
	public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile photo) throws IOException{
		
		String originalFileName = photo.getOriginalFilename();
		Optional<String> extension = Optional.ofNullable(originalFileName).filter(f -> f.contains("."))
			      .map(f -> f.substring(originalFileName.lastIndexOf(".") + 1));
		String fileName = UUID.randomUUID().toString() + '.'+extension.get();
		
		String rootPath = "j2e_monument/src/main/upload/static/";
		File dir = new File(rootPath + File.separator + "images");
		if (!dir.exists()) {dir.mkdir();}
		
		File serverFile = new File(dir.getAbsolutePath()+File.separator+fileName);
		
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(photo.getBytes());
		stream.close();
		
		String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/photos/").path(fileName).toUriString(); 
		
		return new ResponseEntity<>(fileUri, HttpStatus.OK);
		
	}
	
	/*Méthode pour récuperer les photos*/
	@RequestMapping(value="/api/photos/{filename:.+}", method=RequestMethod.GET)
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws MalformedURLException{
		
		String rootPath = "j2e_monument/src/main/upload/static/";
		File file = new File(rootPath + File.separator + "images/"+filename);
		Resource resource = new UrlResource(file.toURI());
	
		return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
	}
    
}
