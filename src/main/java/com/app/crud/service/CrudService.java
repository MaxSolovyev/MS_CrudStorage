package com.app.crud.service;

import com.app.crud.model.Document;
import com.app.crud.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrudService {
    private final DocumentRepository documentRepository;

    @Autowired
    public CrudService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document save(Document document) {
        return documentRepository.save(document);
    }

}
