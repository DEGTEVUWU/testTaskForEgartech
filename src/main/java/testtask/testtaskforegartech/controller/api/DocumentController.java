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
import testtask.testtaskforegartech.exception.ResourceNotValidException;
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
    public String index(Model model) {
        List<DocumentDTO> documents = documentService.getAll();
        model.addAttribute("documents", documents);
        return "index";
    }


    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("document", new CreateDocumentDTO());

        return "create-document";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("document") CreateDocumentDTO documentData, Model model) {
        try {
            documentService.create(documentData);
            model.addAttribute("errorMessage", null);
            return "redirect:/api/documents";
        } catch (ResourceNotValidException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "create-document";
        }
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) throws Exception {
        DocumentDTO documentDTO = documentService.findById(id);
        model.addAttribute("document", documentDTO);
        return "edit-document";
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
            return "edit-document";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        documentService.delete(id);
        return "redirect:/api/documents";
    }
}
