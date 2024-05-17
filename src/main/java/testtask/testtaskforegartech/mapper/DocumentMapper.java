package testtask.testtaskforegartech.mapper;

import org.mapstruct.*;
import testtask.testtaskforegartech.dto.CreateDocumentDTO;
import testtask.testtaskforegartech.dto.DocumentDTO;
import testtask.testtaskforegartech.dto.DocumentParamsDTO;
import testtask.testtaskforegartech.dto.UpdateDocumentDTO;
import testtask.testtaskforegartech.model.Document;

@Mapper(
        uses = { ReferenceMapper.class, JsonNullableMapper.class },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class DocumentMapper {
    public abstract Document toDocument(CreateDocumentDTO dto);
    public abstract DocumentDTO toDTO(Document document);
    public abstract void update(UpdateDocumentDTO dto, @MappingTarget Document document);

    @Mapping(source = "title", target = "titleCont")
    @Mapping(source = "number", target = "number")
    @Mapping(source = "author", target = "authorCont")
    @Mapping(source = "type", target = "typeCont")
    @Mapping(source = "content", target = "contentCont")
    public abstract DocumentParamsDTO toDocumentParamsDTO(Document document);
}
