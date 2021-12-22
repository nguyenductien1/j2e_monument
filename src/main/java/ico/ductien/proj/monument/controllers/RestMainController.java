package ico.ductien.proj.monument.controllers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;



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
	//--------------------------------------------------------------------------------------------//
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
  //--------------------------------------------------------------------------------------------//
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
    //--------------------------------------------------------------------------------------------//
    
    /*Methode pour rajouter un monument*/
	@RequestMapping(value="/api/monuments", method=RequestMethod.POST)
	public Monument addMonument(@RequestBody Monument monument){
		return monumentService.addMonument(monument);
	}
	
	/*Methode pour rajouter un monument*/
	@RequestMapping(value="/api/monuments", method=RequestMethod.PATCH)
	public Monument editMonument(@RequestBody Monument monument, @PathVariable String id){
		return monumentService.addMonument(monument);
	}
	
	/*Methode pour prendre tous les monuments*/
	@RequestMapping(value="/api/monuments", method=RequestMethod.GET)
	public List<Monument> getAllMonuments() {
		return monumentService.getListAllMonuments();
	}
    
}
