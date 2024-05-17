package testtask.testtaskforegartech.controller.api;

import lombok.AllArgsConstructor;
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
import testtask.testtaskforegartech.service.DocumentService;

import java.util.List;

@RequestMapping(path = "/api/documents")
@AllArgsConstructor
@Controller
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping(path = "")
    public String index(Model model, DocumentParamsDTO params) {
        List<DocumentDTO> documents = documentService.getAll(params);
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

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DocumentDTO> show(@PathVariable Long id) {
        DocumentDTO document = documentService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(document);
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
