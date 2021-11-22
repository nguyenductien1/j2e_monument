package ico.ductien.proj.monument.controllers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;



import ico.ductien.proj.monument.service.*;
import ico.ductien.proj.monument.entities.*;
import ico.ductien.proj.monument.repository.DepartementRepository;

@RestController
public class MainController {

 
	@Autowired
    private AppService appService;
	
	
	@RequestMapping("/")
   	public String home(){
          return "This is what i was looking for";                      
     }
	
    
	@RequestMapping(value="/departement/add", method=RequestMethod.POST, headers="Accept=application/json")
    public Departement dep (@RequestBody String departement) {
		
		JSONObject depJSON = new JSONObject(departement);
		String nomDep = depJSON.getString("nomDep");
		String chefLieu = depJSON.getString("chefLieu");
		String dep = depJSON.getString("dep");
		String reg = depJSON.getString("reg");
		Lieu chef = appService.getLieu(chefLieu);
		Departement dept = new Departement(dep, nomDep, chef, reg);
        return appService.addDepartement(dept);
       
	}
	
	@RequestMapping(value="/departement/update", method=RequestMethod.POST, headers="Accept=application/json")
	public Departement updateDepartement (@RequestBody String updateData) {
		
		JSONObject depJSON = new JSONObject(updateData);
		String numDep = depJSON.getString("numDep");
		String codeInseeChefLieu = depJSON.getString("codeInseeChefLieu");
		
        return appService.updateDepartement(codeInseeChefLieu, numDep);
       
	}
	
	public void updateDep(String numDep, Lieu lieu) {
		appService.updateDepartement(numDep, numDep);
	}

    @RequestMapping(value="/departement/all", method=RequestMethod.GET)
	public List<Departement> getAllDepartements() {
		return appService.getListAllDepartements();
	}
    //Lieu 
    @RequestMapping(value="/lieu/add", method=RequestMethod.POST)
    public Lieu addLieu(@RequestBody Lieu lieu){
        return appService.addLieu(lieu);
    }

    @RequestMapping(value="/lieu/all", method=RequestMethod.GET)
	public List<Lieu> getAllLieux() {
		return appService.getListAllLieux();
	}

	//Monument
	@RequestMapping(value="/monument/add", method=RequestMethod.POST)
	public Monument monument(@RequestBody Monument monument){
		return appService.addMonument(monument);
	}
	
	@RequestMapping(value="/monument/all", method=RequestMethod.GET)
	public List<Monument> getAllMonuments() {
		return appService.getListAllMonuments();
	}
    
}
