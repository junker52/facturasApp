package com.learning.facturas.app;

import com.learning.facturas.app.services.UploadPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FacturasAppApplication implements CommandLineRunner {


	@Autowired
	private UploadPictureService uploadPictureService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(FacturasAppApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		this.uploadPictureService.deleteAll();
		this.uploadPictureService.init();

        String pass = "123";
        for (int i = 0; i < 2; i++) {
            String encodedPass = this.bCryptPasswordEncoder.encode(pass);
            System.out.println(encodedPass);
        }
    }
}
