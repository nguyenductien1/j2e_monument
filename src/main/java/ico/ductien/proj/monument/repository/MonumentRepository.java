package ico.ductien.proj.monument.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ico.ductien.proj.monument.entities.Monument;

@Repository
public interface MonumentRepository extends JpaRepository<Monument, String> {
	
	
	public Page<Monument>findByNomM(String nomM, Pageable pageable);
	
	@Query("select m from Monument m where m.codeM=:x")
	public Monument findByCode(@Param("x")String codeM);


}