package ico.ductien.proj.monument.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ico.ductien.proj.monument.entities.Celebrite;

@Service
public interface CelebriteService {
	
	public Celebrite addCelebrite(Celebrite celebrite);
	public void deleteCelebrite(Celebrite celebrite);
	public List<Celebrite> getAllCelebrite();
	public Celebrite findCelebirteByNumber(int numCelebrite);

}
