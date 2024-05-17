package testtask.testtaskforegartech.component;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import testtask.testtaskforegartech.model.Document;
import testtask.testtaskforegartech.repository.DocumentRepository;
import net.datafaker.Faker;
import java.time.ZoneId;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final DocumentRepository documentRepository;
    private static final Faker faker = new Faker();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createDocuments(7);
    }

    public void createDocuments(int count) {
        for (int i = 0; i < count; i++) {
            Document document = new Document();
            document.setId((long) i + 1);
            document.setTitle(faker.book().title());
            document.setAuthor(faker.book().author());
            document.setContent(faker.lorem().paragraph());
            document.setType(faker.book().genre());
            document.setCreationDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            document.setNumber(faker.number().randomNumber());

            documentRepository.save(document);
        }
    }
}
