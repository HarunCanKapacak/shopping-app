package com.example.shoppingapp.filestrore.service.s3;

import java.io.File;
import java.io.InputStream;

public interface FileService {

    void save(String id,String contentType, InputStream isFile);


    void delete(String id);

    byte[] get(String id);

}
