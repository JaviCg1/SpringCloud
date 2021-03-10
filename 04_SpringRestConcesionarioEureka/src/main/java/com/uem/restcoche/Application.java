package com.uem.restcoche;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.eureka.EnableEurekaServer;
import com.uem.restcoche.modelo.entidad.Coche;
import com.uem.restcoche.modelo.negocio.GestorCoche;
import com.uem.restcoche.modelo.persistencia.DaoCoche;

@SpringBootApplication
@EnableEurekaServer
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
	
		DaoCoche dao = context.getBean("daoCoche", DaoCoche.class);
		GestorCoche gc = new GestorCoche();

		Coche p = new Coche();
		p.setMatricula("3000 KTX");
		p.setMarca("Mercedes");
		p.setModelo("GLA");
		
		dao.save(p);
		Coche p1 = new Coche();
		p1.setMatricula("3000 xxp");
		p1.setMarca("Vols");
		p1.setModelo("C");
		gc.alta(p1);
		gc.obtener(1);
		
	}

}
