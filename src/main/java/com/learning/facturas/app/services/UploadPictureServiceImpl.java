package com.learning.facturas.app.services;

import com.learning.facturas.app.models.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by Ricard on 09/07/2018.
 */
@Service
public class UploadPictureServiceImpl implements UploadPictureService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Path UPLOAD_IMAGES_PATH = Paths.get("uploads");

    @Override
    public void loadPicture(Cliente cliente, MultipartFile foto) {
        if (!foto.isEmpty()) {
            //Path recursos = Paths.get("src//main//resources//static//uploads");
            //String rootPath = recursos.toFile().getAbsolutePath();
            //String rootPath = "C://temp//uploads"; //ResourceHandler added
            String uniqueFilename = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename();
            Path rootPath = UPLOAD_IMAGES_PATH.resolve(uniqueFilename);
            Path rootAbsolutePath = rootPath.toAbsolutePath();
            try {
                //byte[] imageBytes = foto.getBytes();
                //Path completePath = Paths.get(rootPath+"//"+foto.getOriginalFilename());
                //Files.write(completePath,imageBytes);
                this.copy(foto, rootAbsolutePath);
                this.log.info("Saved " + uniqueFilename + " in " + rootAbsolutePath.toString());
                //cliente.setFoto(foto.getOriginalFilename());
                cliente.setFoto(uniqueFilename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void copy(MultipartFile foto, Path rootAbsolutePath) throws IOException {
        Files.copy(foto.getInputStream(), rootAbsolutePath);
    }

    @Override
    public void delete(Cliente cliente) {
        if (cliente.getFoto() != null && !cliente.getFoto().isEmpty()) {
            URI fotoPath = UPLOAD_IMAGES_PATH.resolve(cliente.getFoto()).toAbsolutePath().toUri();
            File foto = new File(fotoPath);
            if (foto.exists() && foto.canRead()) {
                foto.delete();
            }
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(UPLOAD_IMAGES_PATH.toFile());
    }

    @Override
    public void init() throws IOException {
        Files.createDirectories(UPLOAD_IMAGES_PATH);
    }
}
