package ico.ductien.proj.monument;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"ico.ductien.proj.monument.*"})
public class MonumentApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(MonumentApplication.class, args);
	}

			
}
