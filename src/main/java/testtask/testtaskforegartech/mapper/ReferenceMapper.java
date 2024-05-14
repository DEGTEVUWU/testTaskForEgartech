package testtask.testtaskforegartech.mapper;

import jakarta.persistence.EntityManager;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.TargetType;
import org.springframework.beans.factory.annotation.Autowired;
import testtask.testtaskforegartech.model.BaseEntity;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public class ReferenceMapper {
    @Autowired
    private EntityManager entityManager; //интерфейс для взаимодействия в БД в JTA

    public <T extends BaseEntity> T toEntity(Long id, @TargetType Class<T> entityClass) {
        return id != null ? entityManager.find(entityClass, id) : null;
    }
}
