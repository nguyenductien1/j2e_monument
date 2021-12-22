package ico.ductien.proj.monument.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ico.ductien.proj.monument.entities.Celebrite;

@Repository
public interface CelebriteRepository extends JpaRepository<Celebrite, Long> {
	
	
	@Query("select c from Celebrite c where  c.nom=:x")
	public Celebrite getCelebrite(@Param("x") String nom);
	
	@Query("select c from Celebrite c where  c.nom like :x")
	public Page<Celebrite> findCelebritetByName(@Param("x")String mc, Pageable pageable);
	
	@Query("select c from Celebrite c where c.numCelebrite=:x")
	public Celebrite findCelebirteByNumber(@Param("x") int number);
	
	@Modifying
	@Query("update Celebrite c set c.nom = ?1, c.prenom = ?2, c.photoUrl =?3, c.nationalite=?4, c.epoque =?5 where c.numCelebrite = ?6")
	void setCelebriteInfoById(String nom, String prenom, String photoUrl, String nationalite, String epoque, int numCelebrite);
	

}
