package testtask.testtaskforegartech.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class DocumentParamsDTO {
    private String titleCont;
    private Long number;
    private String authorCont;
    private String contentCont;
    private String typeCont;

    @DateTimeFormat(pattern = "yyyy-MM-dd") // Указываем формат даты
    private LocalDate creationDate;
}
