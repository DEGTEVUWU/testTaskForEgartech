package testtask.testtaskforegartech.controller.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import testtask.testtaskforegartech.dto.CreateDocumentDTO;
import testtask.testtaskforegartech.dto.DocumentDTO;
import testtask.testtaskforegartech.dto.UpdateDocumentDTO;
import testtask.testtaskforegartech.model.Document;
import testtask.testtaskforegartech.service.DocumentService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/documents")
@AllArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DocumentDTO>> index() {

        List<DocumentDTO> documents = documentService.getAll();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(documents.size()))
                .body(documents);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DocumentDTO> create(@Valid @RequestBody CreateDocumentDTO documentData)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        DocumentDTO documentDTO =  documentService.create(documentData);
        return ResponseEntity.status(HttpStatus.CREATED).body(documentDTO);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DocumentDTO> show(@PathVariable Long id) throws Exception {
        DocumentDTO documentDTO = documentService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(documentDTO);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DocumentDTO> update(@RequestBody @Valid UpdateDocumentDTO documentData,
                                                @PathVariable Long id) throws Exception {
        DocumentDTO documentDTO = documentService.update(documentData, id);
        return ResponseEntity.status(HttpStatus.OK).body(documentDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        documentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
