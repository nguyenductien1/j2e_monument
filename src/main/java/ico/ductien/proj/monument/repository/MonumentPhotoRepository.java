package ico.ductien.proj.monument.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ico.ductien.proj.monument.entities.MonumentPhotos;

@Repository
public interface MonumentPhotoRepository extends JpaRepository<MonumentPhotos, String>{
	@Query("Select mp.photoUri from MonumentPhotos mp where mp.Monument=:monumentid")
	public List<String> findPhotosByMonument(@Param("monumentid") String monumentid);

}
