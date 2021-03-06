package ico.ductien.proj.monument.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ico.ductien.proj.monument.entities.Lieu;
import ico.ductien.proj.monument.entities.Monument;
import java.util.List;

@Repository
public interface LieuRepository extends JpaRepository<Lieu, String> {
	
	
	@Query("select l from Lieu l where l.nomCom=:x")
	public List<Lieu> findLieuBynomCom(@Param ("x")String nomCom);
	
	//@Query("Select l from lieu l where l.nomCom like %:nomCom%")
	public List<Lieu> findByNomComContaining(@Param("%:nomCom%:") String nomCom);

	@Query("select l from Lieu l where l.codeInsee=:x")
	public Lieu getLieu(@Param("x")String codeInsee);
	
	@Query("select m from Monument m, Lieu l where m.lieu.codeInsee = l.codeInsee and l.codeInsee=:x")
	public List<Monument> getMonumentsbyLieu(@Param ("x")String codeInsee);

}
