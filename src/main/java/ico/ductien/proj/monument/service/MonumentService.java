package ico.ductien.proj.monument.service;
import org.springframework.stereotype.Service;

import ico.ductien.proj.monument.entities.Celebrite;
import ico.ductien.proj.monument.entities.Monument;

import java.util.*;

@Service
public interface MonumentService {
	 //Methods for the monuments
    public Monument addMonument(Monument monument);
    public void addMonumentToLieu(String codeM, String codeInsee);
    public Optional<Monument> getMonument(String codeM);
    public List<Monument> getListAllMonuments();
    public float getDistanceBetweenMonuments(String nomMonumentD, String nomMonumentA);
    public List<Monument> getListMonumentsByDep(String nomDep);
    public List<Monument> getListMonumentsByLieu(String nomLieu);
    public Monument findByCode(String codeM);
    public void deleteMonument(Monument monument);
    public void saveMonument(Set<Celebrite> celebrite, String codeM);
    public Monument saveMonumentV2(Monument monument);
    

}
