package ico.ductien.proj.monument.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ico.ductien.proj.monument.entities.Celebrite;
import ico.ductien.proj.monument.repository.CelebriteRepository;

@Service
@Transactional
public class CelebriteServiceImplementations implements CelebriteService {
	@Autowired
	private CelebriteRepository celebriteRepository;

	@Override
	public Celebrite addCelebrite(Celebrite celebrite) {
		
		return celebriteRepository.save(celebrite);
	}

	@Override
	public void deleteCelebrite(Celebrite celebrite) {
		celebriteRepository.delete(celebrite);
		
	}

	@Override
	public List<Celebrite> getAllCelebrite() {
		return celebriteRepository.findAll();
	}

	@Override
	public Celebrite findCelebirteByNumber(int numberCelebrite) {
		return (Celebrite) celebriteRepository.findCelebirteByNumber(numberCelebrite);
	}

	@Override
	public void setInfoCelebrite(String nom, String prenom, String photoUrl, String nationalite, String epoque, int numCelebrite) {
		celebriteRepository.setCelebriteInfoById(nom, prenom, photoUrl,nationalite, epoque, numCelebrite);
		
	}

}
