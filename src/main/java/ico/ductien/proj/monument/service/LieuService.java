package ico.ductien.proj.monument.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ico.ductien.proj.monument.entities.Lieu;

@Service
public interface LieuService {
	
    public Lieu addLieu(Lieu lieu);
    public void deleteLieu(Lieu lieu);
    public Lieu getLieu(String codeInsee);
    public List<Lieu> getListAllLieux();
}
