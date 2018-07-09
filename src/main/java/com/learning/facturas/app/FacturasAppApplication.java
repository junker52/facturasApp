package com.learning.facturas.app;

import com.learning.facturas.app.services.UploadPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FacturasAppApplication implements CommandLineRunner {


	@Autowired
	private UploadPictureService uploadPictureService;

	public static void main(String[] args) {
		SpringApplication.run(FacturasAppApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		this.uploadPictureService.deleteAll();
		this.uploadPictureService.init();
	}
}
