package testtask.testtaskforegartech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testtask.testtaskforegartech.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
