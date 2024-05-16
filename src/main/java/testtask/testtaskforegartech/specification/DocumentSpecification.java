package testtask.testtaskforegartech.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import testtask.testtaskforegartech.dto.DocumentParamsDTO;
import testtask.testtaskforegartech.model.Document;

import java.time.LocalDate;

@Component
public class DocumentSpecification {
    public Specification build(DocumentParamsDTO params) {
        return withTitleCount(params.getTitleCont())
                .and(withAuthorCount(params.getAuthorCont()))
                .and(withContentCount(params.getContentCont()))
                .and(withTypeCount(params.getTypeCont()))
                .and(withNumber(params.getNumber()))
                .and(withCreatedDate(params.getCreationDate()));
    }

    private Specification<Document> withTitleCount(String titleCont) {
        return (root, query, cb) -> titleCont == null || titleCont.isEmpty()
                ? cb.conjunction()
                : cb.like((root.get("title")), "%" + titleCont + "%");
    }

    private Specification<Document> withNumber(Long number) {
        return (root, query, cb) -> number == null
                ? cb.conjunction()
                : cb.equal(root.get("number"), number);
    }

    private Specification<Document> withAuthorCount(String authorCont) {
        return (root, query, cb) -> authorCont == null
                ? cb.conjunction()
                : cb.like((root.get("author")), "%" + authorCont + "%");
    }
    private Specification<Document> withContentCount(String contentCont) {
        return (root, query, cb) -> contentCont == null
                ? cb.conjunction()
                : cb.like((root.get("content")), "%" + contentCont + "%");
    }
    private Specification<Document> withTypeCount(String typeCont) {
        return (root, query, cb) -> typeCont == null
                ? cb.conjunction()
                : cb.like((root.get("type")), "%" + typeCont + "%");
    }
    private Specification<Document> withCreatedDate(LocalDate date) {
        return (root, query, cb) -> date == null
                ? cb.conjunction()
                : cb.equal(root.get("creationDate"), date);
    }
}
