package testtask.testtaskforegartech.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDocumentDTO {
    private String title;
    private Long number;
    private String author;
    private String content;
    private String type;
}
