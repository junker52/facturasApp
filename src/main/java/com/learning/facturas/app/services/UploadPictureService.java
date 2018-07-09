package com.learning.facturas.app.services;

import com.learning.facturas.app.models.Cliente;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by Ricard on 09/07/2018.
 */
public interface UploadPictureService {

    void loadPicture(Cliente cliente, MultipartFile foto);

    void copy(MultipartFile foto, Path rootAbsolutePath) throws IOException;

    void delete(Cliente cliente);

}
