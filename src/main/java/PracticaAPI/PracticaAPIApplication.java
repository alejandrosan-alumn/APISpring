package PracticaAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/*@SpringBootApplication(scanBasePackages={
    "com.sanvalero.myshop.service.VueloService", "com.sanvalero.myshop.service.ProductService"
})*/
//@ComponentScan(basePackages = {"com.sanvalero.myshop.service.VueloService"})
public class PracticaAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticaAPIApplication.class, args);
	}
}
