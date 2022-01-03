package ico.ductien.proj.monument.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ico.ductien.proj.monument.entities.Monument;
import ico.ductien.proj.monument.repository.MonumentRepository;

@Service
@Transactional
public class MonumentServiceImplementations implements MonumentService {
	@Autowired
	private MonumentRepository monumentRepository;
	
	
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
	public Monument findByCode(String codeM) {
		// TODO Auto-generated method stub
		return monumentRepository.findByCode(codeM);
	}

	@Override
	public void deleteMonument(Monument monument) {
		monumentRepository.delete(monument);
		
	}


}
