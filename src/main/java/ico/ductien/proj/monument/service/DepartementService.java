package ico.ductien.proj.monument.service;

import org.springframework.stereotype.Service;
import java.util.List;

import ico.ductien.proj.monument.entities.Departement;


@Service
public interface DepartementService {
	public Departement addDepartement(Departement departement);
    public Departement getDepartement(String numDep);
    public Departement updateDepartement(String numInseeChefLieu, String numdep);
    public List<Departement> getListAllDepartements();
    public void deleteDepartement(Departement departement);
}
