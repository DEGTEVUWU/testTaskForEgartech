package testtask.testtaskforegartech.controller.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import testtask.testtaskforegartech.dto.CreateDocumentDTO;
import testtask.testtaskforegartech.dto.DocumentDTO;
import testtask.testtaskforegartech.dto.DocumentParamsDTO;
import testtask.testtaskforegartech.dto.UpdateDocumentDTO;
import testtask.testtaskforegartech.exception.ResourceNotValidException;
import testtask.testtaskforegartech.mapper.DocumentMapper;
import testtask.testtaskforegartech.model.Document;
import testtask.testtaskforegartech.repository.DocumentRepository;
import testtask.testtaskforegartech.service.DocumentService;
import testtask.testtaskforegartech.specification.DocumentSpecification;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

//@RestController
@RequestMapping(path = "/api/documents")
@AllArgsConstructor
@Controller
public class DocumentController {
    private final DocumentService documentService;
    private final DocumentSpecification documentSpecification;
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

//    @GetMapping(path = "")
//    @ResponseStatus(HttpStatus.OK)
//    public Page<DocumentDTO> index(DocumentParamsDTO params, @RequestParam(defaultValue = "1") int page) {
//        var spec = documentSpecification.build(params);
//        Page<Document> documents = documentRepository.findAll(spec, PageRequest.of(page - 1, 10));
//        Page<DocumentDTO> result = documents.map(documentMapper::toDTO);
//
//        return result;
//    }

    @GetMapping(path = "")
    public String index(Model model, DocumentParamsDTO params) {

        System.out.println(params);

        List<DocumentDTO> documents = documentService.getAll(params);

        System.out.println(documents.stream().toList());
        System.out.println("-----------------------------------");

        model.addAttribute("documents", documents);
        model.addAttribute("params", params);
        return "index";
    }


    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("document", new CreateDocumentDTO());

        return "document-form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("document") CreateDocumentDTO documentData, Model model) {
        try {
            documentService.create(documentData);
            model.addAttribute("errorMessage", null);
            return "redirect:/api/documents";
        } catch (ResourceNotValidException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "document-form";
        }
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) throws Exception {
        DocumentDTO documentDTO = documentService.findById(id);
        model.addAttribute("document", documentDTO);
        return "document-form";
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable Long id,
            @ModelAttribute("document") UpdateDocumentDTO documentData,
            Model model
    ) {
        try {
            documentService.update(documentData, id);
            model.addAttribute("errorMessage", null);
            return "redirect:/api/documents";
        } catch (ResourceNotValidException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "document-form";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        documentService.delete(id);
        return "redirect:/api/documents";
    }
}
