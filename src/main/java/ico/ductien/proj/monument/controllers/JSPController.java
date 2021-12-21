package ico.ductien.proj.monument.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ico.ductien.proj.monument.service.AppService;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class JSPController {
	@Autowired
    private AppService appService;
	
	//Rendu du page avec thymeleaf
	@GetMapping(path="/monuments")
	public String monuments(@RequestParam(name = "name", required = false, defaultValue = "anonymous") String name,
			Model model) {
		model.addAttribute("name", name);
		model.addAttribute("monuments", appService.getListAllMonuments() );
		return "monuments";
		
	}
}
