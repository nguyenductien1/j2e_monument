package ico.ductien.proj.monument.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ico.ductien.proj.monument.entities.Celebrite;
import ico.ductien.proj.monument.entities.Departement;
import ico.ductien.proj.monument.entities.Lieu;
import ico.ductien.proj.monument.entities.Monument;
import ico.ductien.proj.monument.repository.CelebriteRepository;
import ico.ductien.proj.monument.repository.DepartementRepository;
import ico.ductien.proj.monument.repository.LieuRepository;
import ico.ductien.proj.monument.repository.MonumentRepository;

@Service
@Transactional
public class AppServiceImplementations implements AppService{

    //Beans injection in Spring. 
    @Autowired
	private DepartementRepository departementRepository;
	
	@Autowired
	private MonumentRepository monumentRepository;
	
	@Autowired
	private LieuRepository lieuRepository;
	
	@Autowired
	private CelebriteRepository celebriteRepository;

	@Autowired
	private AppService appService;
    

    @Override
    public Departement addDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    @Override
    public Departement getDepartement(String numDep) {
        return departementRepository.findOne(numDep);
    }
    
    @Override
	public Departement updateDepartement(String numInseeChefLieu, String numdep) {
    	Lieu chef = lieuRepository.getLieu(numInseeChefLieu);
    	
		Departement departementToUpdate = this.getDepartement(numdep);
		
		departementToUpdate.setChefLieu(chef);
		departementRepository.save(departementToUpdate);
		
		return null;
	}
    
    @Override
    public List<Departement> getListAllDepartements() {
        
        return departementRepository.findAll();
    }

    @Override
    public Lieu addLieu(Lieu lieu) {
 
        return lieuRepository.save(lieu);
    }

    @Override
    public Lieu getLieu(String codeInsee) {

        return lieuRepository.getLieu(codeInsee);
    }

    @Override
    public List<Lieu> getListAllLieux() {

        return lieuRepository.findAll();
    }

    @Override
    public Monument addMonument(Monument monument) {
        
        return monumentRepository.save(monument);
        
    }

    @Override
    public void addMonumentToLieu(String codeM, String codeInsee) {
        
        
    }

    @Override
    public Optional<Monument> getMonument(String codeM) { 
        return monumentRepository.findById(codeM);
    }

    @Override
    public List<Monument> getListAllMonuments() {
        
        return monumentRepository.findAll();
    }

    @Override
    public float getDistanceBetweenMonuments(String nomMonumentD, String nomMonumentA) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Monument> getListMonumentsByDep(String nomDep) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Monument> getListMonumentsByLieu(String nomLieu) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Celebrite addCelebrite(Celebrite celebrite) {
        // TODO Auto-generated method stub
        return null;
    }

	
    
}
