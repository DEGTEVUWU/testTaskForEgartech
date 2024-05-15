package testtask.testtaskforegartech.controller.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import testtask.testtaskforegartech.dto.CreateDocumentDTO;
import testtask.testtaskforegartech.dto.DocumentDTO;
import testtask.testtaskforegartech.dto.UpdateDocumentDTO;
import testtask.testtaskforegartech.model.Document;
import testtask.testtaskforegartech.service.DocumentService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

//@RestController
@RequestMapping(path = "/api/documents")
@AllArgsConstructor
@Controller
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping(path = "")
//    @ResponseStatus(HttpStatus.OK)
    public String index(Model model) {

//        List<DocumentDTO> documents = documentService.getAll();
//        return ResponseEntity.ok()
//                .header("X-Total-Count", String.valueOf(documents.size()))
//                .body(documents);
        List<DocumentDTO> documents = documentService.getAll();
        model.addAttribute("documents", documents);
        return "index";
    }

//    @PostMapping(path = "")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<DocumentDTO> create(@Valid @RequestBody CreateDocumentDTO documentData)
//            throws NoSuchAlgorithmException, InvalidKeySpecException {
//        DocumentDTO documentDTO =  documentService.create(documentData);
//        return ResponseEntity.status(HttpStatus.CREATED).body(documentDTO);
//    }
//
//    @GetMapping(path = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<DocumentDTO> show(@PathVariable Long id) throws Exception {
//        DocumentDTO documentDTO = documentService.findById(id);
//        return ResponseEntity.status(HttpStatus.OK).body(documentDTO);
//    }
//
//    @PutMapping(path = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<DocumentDTO> update(@RequestBody @Valid UpdateDocumentDTO documentData,
//                                                @PathVariable Long id) throws Exception {
//        DocumentDTO documentDTO = documentService.update(documentData, id);
//        return ResponseEntity.status(HttpStatus.OK).body(documentDTO);
//    }
//
//    @DeleteMapping(path = "/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        documentService.delete(id);
//        return ResponseEntity.noContent().build();
//    }

    // Отображение формы создания документа
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("document", new CreateDocumentDTO());
        return "create-document";
    }

    // Обработка формы создания документа
    @PostMapping("/create")
    public String create(@ModelAttribute("document") CreateDocumentDTO documentData) {
        documentService.create(documentData);
        return "redirect:/api/documents";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) throws Exception {
        DocumentDTO documentDTO = documentService.findById(id);
        model.addAttribute("document", documentDTO);
        return "edit-document";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("document") UpdateDocumentDTO documentData) throws Exception {
        documentService.update(documentData, id);
        return "redirect:/api/documents";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        documentService.delete(id);
        return "redirect:/api/documents";
    }
}
