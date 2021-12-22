package ico.ductien.proj.monument.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ico.ductien.proj.monument.entities.Departement;
import ico.ductien.proj.monument.entities.Lieu;
import ico.ductien.proj.monument.repository.DepartementRepository;
import ico.ductien.proj.monument.repository.LieuRepository;

@Service
@Transactional
public class DepartementServiceImplementations implements DepartementService {
	@Autowired
	private DepartementRepository departementRepository;
	
	@Autowired
	private LieuRepository lieuRepository;
	
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

}
