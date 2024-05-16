package testtask.testtaskforegartech.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
@NoArgsConstructor
public class UpdateDocumentDTO {
    private String title;
    private Long number;
    private String author;
    private String content;
    private String type;
}
