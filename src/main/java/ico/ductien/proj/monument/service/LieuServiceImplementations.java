package ico.ductien.proj.monument.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ico.ductien.proj.monument.entities.Lieu;
import ico.ductien.proj.monument.repository.LieuRepository;

@Service
@Transactional
public class LieuServiceImplementations implements LieuService{
	@Autowired
	private LieuRepository lieuRepository;
	@Override
	public Lieu addLieu(Lieu lieu) {
		return lieuRepository.save(lieu);
	}

	@Override
	public void deleteLieu(Lieu lieu) {
		lieuRepository.delete(lieu);
	}

	@Override
	public Lieu getLieu(String codeInsee) {
		return lieuRepository.getLieu(codeInsee);
	}

	@Override
	public List<Lieu> getListAllLieux() {
		return lieuRepository.findAll();
	}
	
}
