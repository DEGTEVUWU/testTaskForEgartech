package testtask.testtaskforegartech.controller.api;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import testtask.testtaskforegartech.dto.CreateDocumentDTO;
import testtask.testtaskforegartech.dto.DocumentDTO;
import testtask.testtaskforegartech.dto.DocumentParamsDTO;
import testtask.testtaskforegartech.dto.UpdateDocumentDTO;
import testtask.testtaskforegartech.service.DocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import testtask.testtaskforegartech.exception.ResourceNotValidException;

public class DocumentControllerTest {

    @Mock
    private DocumentService documentService;

    @Mock
    private Model model;

    @InjectMocks
    private DocumentController documentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIndex() {
        DocumentParamsDTO params = new DocumentParamsDTO();
        List<DocumentDTO> documents = new ArrayList<>();
        documents.add(new DocumentDTO());

        when(documentService.getAll(params)).thenReturn(documents);

        Model model = new BindingAwareModelMap();

        String viewName = documentController.index(model, params);

        assertEquals("index", viewName);
        assertEquals(documents, model.getAttribute("documents"));
        assertEquals(params, model.getAttribute("params"));
        verify(documentService, times(1)).getAll(params);
    }

    @Test
    public void testCreateForm() {
        Model model = new BindingAwareModelMap();
        String viewName = documentController.createForm(model);
        assertEquals("document-form", viewName);
    }

    @Test
    public void testCreateSuccessful() throws Exception {
        CreateDocumentDTO documentData = new CreateDocumentDTO();
        DocumentDTO documentDTO = new DocumentDTO();
        when(documentService.create(documentData)).thenReturn(documentDTO);

        String viewName = documentController.create(documentData, model);

        assertEquals("redirect:/api/documents", viewName);
        verify(model).addAttribute("errorMessage", null);
    }

    @Test
    public void testCreateFailure() throws Exception {
        CreateDocumentDTO documentData = new CreateDocumentDTO();
        String errorMessage = "Document with title Example already exists, creation is impossible!";
        doThrow(new ResourceNotValidException(errorMessage)).when(documentService).create(documentData);

        String viewName = documentController.create(documentData, model);

        assertEquals("document-form", viewName);
        verify(model).addAttribute("errorMessage", errorMessage);
    }

    @Test
    void testEditForm() throws Exception {
        Long documentId = 1L;
        DocumentDTO mockDocumentDTO = new DocumentDTO();
        mockDocumentDTO.setId(documentId);
        when(documentService.findById(anyLong())).thenReturn(mockDocumentDTO);

        Model model = mock(Model.class);
        String viewName = documentController.editForm(documentId, model);

        assertEquals("document-form", viewName);
        verify(documentService, times(1)).findById(documentId);
        verify(model, times(1)).addAttribute(eq("document"), eq(mockDocumentDTO));
    }

    @Test
    void testUpdate() throws ResourceNotValidException {
        Long documentId = 1L;
        UpdateDocumentDTO updateDocumentDTO = new UpdateDocumentDTO();
        Model model = mock(Model.class);

        String viewName = documentController.update(documentId, updateDocumentDTO, model);

        assertEquals("redirect:/api/documents", viewName);
        verify(documentService, times(1)).update(eq(updateDocumentDTO), eq(documentId));
        verify(model, times(1)).addAttribute(eq("errorMessage"), isNull());
    }

    @Test
    void testUpdateResourceNotValidException() throws ResourceNotValidException {
        Long documentId = 1L;
        UpdateDocumentDTO updateDocumentDTO = new UpdateDocumentDTO();
        Model model = mock(Model.class);
        String errorMessage = "Test error message";
        doThrow(new ResourceNotValidException(errorMessage)).when(documentService).update(any(), anyLong());

        String viewName = documentController.update(documentId, updateDocumentDTO, model);

        assertEquals("document-form", viewName);
        verify(documentService, times(1)).update(eq(updateDocumentDTO), eq(documentId));
        verify(model, times(1)).addAttribute(eq("errorMessage"), eq(errorMessage));
    }

    @Test
    void testShow() throws ResourceNotValidException {
        Long documentId = 1L;
        DocumentDTO expectedDocumentDTO = new DocumentDTO();
        when(documentService.findById(documentId)).thenReturn(expectedDocumentDTO);

        ResponseEntity<DocumentDTO> response = documentController.show(documentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDocumentDTO, response.getBody());
        verify(documentService, times(1)).findById(eq(documentId));
    }
    @Test
    void testDelete() {
        Long documentId = 1L;
        String viewName = documentController.delete(documentId);
        assertEquals("redirect:/api/documents", viewName);
        verify(documentService, times(1)).delete(eq(documentId));
    }
}
