package com.app.crud.rest;

import com.app.crud.dto.DocInfo;
import com.app.crud.feignClient.IndexerClient;
import com.app.crud.model.Document;
import com.app.crud.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
public class CrudController {
    private final String fileStorageLocation = "c:\\fileStorage";
    private final CrudService crudService;
    private final IndexerClient indexerClient;

    @Autowired
    public CrudController(CrudService crudService, IndexerClient indexerClient) {
        this.crudService = crudService;
        this.indexerClient = indexerClient;
    }

    @RequestMapping(value="/crud", method = RequestMethod.POST)
    ResponseEntity<String> keepDocument(@RequestParam("file") MultipartFile file) {
        String content = null;
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path targetLocation = Paths.get(this.fileStorageLocation).resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            content = new String(file.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Document document = new Document(file.getOriginalFilename());

        Long id = crudService.save(document).Id;

        DocInfo docInfo = new DocInfo(id, content);

        String result = indexerClient.keepIndex(docInfo);

        return ResponseEntity.ok(result);
    }
}