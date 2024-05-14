package testtask.testtaskforegartech.dto;

import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
public class UpdateDocumentDTO {
    private String title;
    private String author;
    private String content;
    private String type;
}
