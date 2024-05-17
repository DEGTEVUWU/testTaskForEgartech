package testtask.testtaskforegartech.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
