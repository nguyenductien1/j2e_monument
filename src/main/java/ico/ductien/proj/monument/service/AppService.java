package ico.ductien.proj.monument.service;

import org.springframework.stereotype.Service;

import ico.ductien.proj.monument.entities.Departement;
import ico.ductien.proj.monument.entities.Monument;
import ico.ductien.proj.monument.entities.MonumentPhotos;
import ico.ductien.proj.monument.entities.Lieu;
import ico.ductien.proj.monument.entities.Celebrite;
import java.util.*;

@Service
public interface AppService {
    //Methods for the departements
    public Departement addDepartement(Departement departement);
    public Departement getDepartement(String numDep);
    public Departement updateDepartement(String numInseeChefLieu, String numdep);
    public List<Departement> getListAllDepartements();

    //Methods for Lieu
    public Lieu addLieu(Lieu lieu);
    public Lieu getLieu(String codeInsee);
    public List<Lieu> getListAllLieux();

    //Methods for the monuments
    public Monument addMonument(Monument monument);
    public void addMonumentToLieu(String codeM, String codeInsee);
    public Optional<Monument> getMonument(String codeM);
    public List<Monument> getListAllMonuments();
    public float getDistanceBetweenMonuments(String nomMonumentD, String nomMonumentA);
    public List<Monument> getListMonumentsByDep(String nomDep);
    public List<Monument> getListMonumentsByLieu(String nomLieu);
    public List<String> findPhotosByMonument(String monumentID);
    public void addPhotosToMonument(MonumentPhotos monumentPhoto);

    //Methods for Celebrite 
    public Celebrite addCelebrite(Celebrite celebrite);
    
}
