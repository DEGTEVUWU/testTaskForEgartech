package testtask.testtaskforegartech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testtask.testtaskforegartech.model.Document;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    boolean existsDocumentByTitle(String title);
    Optional<Document> findDocumentByTitle(String title);
}
