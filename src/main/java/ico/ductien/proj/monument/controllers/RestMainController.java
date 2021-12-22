package ico.ductien.proj.monument.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;

import java.util.Set;



import ico.ductien.proj.monument.service.*;
import ico.ductien.proj.monument.entities.*;
import ico.ductien.proj.monument.repository.DepartementRepository;

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
	
	
	
	
	@RequestMapping("/")
   	public String home(){
          return "This is what i was looking for";                      
     }
	//-------------------------------DEPARTEMENT----------------------------------------//
    /* Rajouter un département */
	@RequestMapping(value="/api/departements", method=RequestMethod.POST, headers="Accept=application/json")
    public Departement dep (@RequestBody String departement) {
		
		JSONObject depJSON = new JSONObject(departement);
		String nomDep = depJSON.getString("nomDep");
		String chefLieu = depJSON.getString("chefLieu");
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
	
	public void updateDep(String numDep, Lieu lieu) {
		departementService.updateDepartement(numDep, numDep);
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
    
    /*Méthode pour modifier un lieu*/
    @RequestMapping(value="/api/lieux/{id}", method=RequestMethod.PATCH)
    public Lieu editLieu(@RequestBody Lieu lieu,  @PathVariable String id){
        return lieuService.addLieu(lieu);
    }
    
    /*Méthode pour supprimer un lieu*/
    @RequestMapping(value="/api/lieux/{id}", method=RequestMethod.DELETE)
    public Lieu deleteLieu(@RequestBody Lieu lieu,  @PathVariable String id){
        return lieuService.addLieu(lieu);
    }
    
    /*Méthode pour prendre tous les lieux*/
    @RequestMapping(value="/api/lieux", method=RequestMethod.GET)
	public List<Lieu> getAllLieux() {
		return lieuService.getListAllLieux();
	}
    //---------------------------------MONUMENTS--------------------------------------------//
    
    /*Methode pour rajouter un monument*/
	@RequestMapping(value="/api/monuments", method=RequestMethod.POST)
	public Monument addMonument(@RequestBody Monument monument){
		return monumentService.addMonument(monument);
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
//			monument.setCodeLieu(monumentJson.getString("codeLieu"));
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
	public List<Monument> getAllMonuments() {
		return monumentService.getListAllMonuments();
	}
	
	/*Méthode pour ajouter un celebrite dans un monument*/
	@RequestMapping(value="/api/monuments/celebrites", method=RequestMethod.POST )
	public ResponseEntity<String> addCelebriteToMonument(@RequestBody String monu_celeb){
		
		JSONObject monuCelebJSON = new JSONObject(monu_celeb);
		Monument monument = monumentService.findByCode(monuCelebJSON.getString("codeM"));
		Celebrite celebrite = celebriteService.findCelebirteByNumber(monuCelebJSON.getInt("numCelebrite"));
		Set<Celebrite> setCelebrite = monument.getAssociea_celebrite();
		setCelebrite.add(celebrite);
		monument.setAssociea_celebrite(setCelebrite);
		monumentService.addMonument(monument);
		
		return new ResponseEntity<String>("Added", HttpStatus.ACCEPTED);
	}
	
	//----------------------------------CELEBRITES----------------------------------------------//
	
	/*Méthode pour rajouter un célébrite*/
	@RequestMapping(value="/api/celebrites", method=RequestMethod.POST)
	public ResponseEntity<String> addCelebrite(@RequestBody String celebrite){
		JSONObject celebJSON = new JSONObject(celebrite);
		
		Celebrite celeb = new Celebrite();
		celeb.setNom(celebJSON.getString("nom"));
		celeb.setPrenom(celebJSON.getString("prenom"));
		celeb.setNationalite(celebJSON.getString("nationalite"));
		celeb.setEpoque(celebJSON.getString("epoque"));
		celebriteService.addCelebrite(celeb);
		return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
		
	}
	
	/*Méthode pour modifier un célébrite*/
	@RequestMapping(value="/api/celebrites/{numCelebrite}", method=RequestMethod.PATCH)
	public ResponseEntity<String> editCelebrite(@RequestBody String celebrite, @PathVariable int numCelebrite){
		JSONObject celebJSON = new JSONObject(celebrite);
		
		celebriteService.setInfoCelebrite(celebJSON.getString("nom"), celebJSON.getString("prenom"), celebJSON.getString("photoURL"), celebJSON.getString("nationalite"), celebJSON.getString("epoque"), numCelebrite);
		
		return new ResponseEntity<String>("Modified", HttpStatus.ACCEPTED);
	}
    
}
