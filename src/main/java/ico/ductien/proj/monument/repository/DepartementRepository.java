package ico.ductien.proj.monument.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ico.ductien.proj.monument.entities.Departement;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, String> {

	@Query("select d from Departement d where d.dep=:x")
	Departement findOne(@Param("x") String dep);
	
	public List<Departement> findDepartementBynomDep(String nomDep);
	
	public List<Departement> findByNomDepContaining(String nomDep);


}
