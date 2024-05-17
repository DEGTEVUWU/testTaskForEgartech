package testtask.testtaskforegartech.util;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import testtask.testtaskforegartech.dto.CreateDocumentDTO;
import testtask.testtaskforegartech.dto.DocumentDTO;
import testtask.testtaskforegartech.dto.DocumentParamsDTO;
import testtask.testtaskforegartech.model.Document;

@Getter
//@Component
public class ModelGenerator {
//
//    private Model<Document> documentModel;
//
//    @Autowired
//    private Faker faker;

//    @PostConstruct
//    private void init() {
//        documentModel = Instancio.of(Document.class)
//                .ignore(Select.field(Document::getId))
//                .supply(Select.field(Document::getNumber), () -> faker.number())
//                .supply(Select.field(Document::getAuthor), () -> faker.name().firstName())
//                .supply(Select.field(Document::getContent), () -> faker.text())
//                .supply(Select.field(Document::getTitle), () -> faker.name().title())
//                .toModel();
//    }

    public Document newDocument() {
        Faker faker = new Faker();
        return Instancio.of(Document.class)
                .ignore(Select.field(Document::getId))
                .supply(Select.field(Document::getNumber), () -> faker.number())
                .supply(Select.field(Document::getAuthor), () -> faker.name().firstName())
                .supply(Select.field(Document::getContent), () -> faker.text())
                .supply(Select.field(Document::getTitle), () -> faker.name().title())
                .create();
    }
    public DocumentParamsDTO newDocumentParamsDTO() {
        Faker faker = new Faker();
        return Instancio.of(DocumentParamsDTO.class)
                .supply(Select.field(DocumentParamsDTO::getNumber), () -> faker.number().randomNumber())
                .supply(Select.field(DocumentParamsDTO::getAuthorCont), () -> faker.name().firstName())
                .supply(Select.field(DocumentParamsDTO::getContentCont), () -> faker.text().text())
                .supply(Select.field(DocumentParamsDTO::getTitleCont), () -> faker.name().title())
                .supply(Select.field(DocumentParamsDTO::getTypeCont), () -> faker.name().title())
                .create();
    }
    public DocumentDTO newDocumentDTO() {
        Faker faker = new Faker();
        return Instancio.of(DocumentDTO.class)
                .ignore(Select.field(DocumentDTO::getId))
                .supply(Select.field(DocumentDTO::getNumber), () -> faker.number().randomNumber())
                .supply(Select.field(DocumentDTO::getAuthor), () -> faker.name().firstName())
                .supply(Select.field(DocumentDTO::getContent), () -> faker.text().text())
                .supply(Select.field(DocumentDTO::getTitle), () -> faker.name().title())
                .supply(Select.field(DocumentDTO::getType), () -> faker.name().title())
                .create();
    }
    public CreateDocumentDTO newCreateDocumentDTO() {
        Faker faker = new Faker();
        return Instancio.of(CreateDocumentDTO.class)
                .supply(Select.field(CreateDocumentDTO::getNumber), () -> faker.number().randomNumber())
                .supply(Select.field(CreateDocumentDTO::getAuthor), () -> faker.name().firstName())
                .supply(Select.field(CreateDocumentDTO::getContent), () -> faker.text().text())
                .supply(Select.field(CreateDocumentDTO::getTitle), () -> faker.name().title())
                .supply(Select.field(CreateDocumentDTO::getType), () -> faker.name().title())
                .create();
    }
}
