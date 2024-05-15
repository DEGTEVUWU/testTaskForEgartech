package testtask.testtaskforegartech.service;

import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import testtask.testtaskforegartech.dto.CreateDocumentDTO;
import testtask.testtaskforegartech.dto.DocumentDTO;
import testtask.testtaskforegartech.dto.UpdateDocumentDTO;
import testtask.testtaskforegartech.exception.ResourceNotValidException;
import testtask.testtaskforegartech.mapper.DocumentMapper;
import testtask.testtaskforegartech.model.Document;
import testtask.testtaskforegartech.repository.DocumentRepository;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    public List<DocumentDTO> getAll() {
        List<Document> documents = documentRepository.findAll();
        List<DocumentDTO> result = documents.stream()
                .map(documentMapper::toDTO)
                .toList();
        return result;
    }

    public DocumentDTO create(CreateDocumentDTO documentData) throws ResourceNotValidException {
        Document document = documentMapper.toDocument(documentData);

        if (isDocumentNotValidForCreate(document)) {
            throw new ResourceNotValidException("Document with title " + document.getTitle()
                    + " already exists, creation is impossible!");
        }
        documentRepository.save(document);
        DocumentDTO documentDTO = documentMapper.toDTO(document);
        return documentDTO;
    }
    public DocumentDTO findById(Long id) throws ResourceNotValidException {
        var document = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotValidException("Document with this id " + id + " not found!"));
        DocumentDTO documentDTO = documentMapper.toDTO(document);
        return documentDTO;
    }

    public DocumentDTO update(UpdateDocumentDTO documentData, Long id) throws ResourceNotValidException {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotValidException("Document with this id " + id + " not found!"));

        documentMapper.update(documentData, document);
        documentRepository.save(document);
        DocumentDTO documentDTO = documentMapper.toDTO(document);
        return documentDTO;
    }

    public void delete(Long id) {
        documentRepository.deleteById(id);
    }

    private boolean isDocumentNotValidForCreate(Document currentDocument) {
        return documentRepository.existsDocumentByTitle(currentDocument.getTitle());
    }
}
