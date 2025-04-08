package fr.inventivit.DreamCaseApp.mapper;

import fr.inventivit.DreamCaseApp.dto.Case.request.CaseRequest;
import fr.inventivit.DreamCaseApp.dto.Case.response.CaseResponse;
import fr.inventivit.DreamCaseApp.models.Case;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CaseMapper {
    CaseResponse toResponse(Case entity);

    @Mapping(target = "caseId", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    Case toEntity(CaseRequest dto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "caseId", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    void updateFromDto(CaseRequest dto, @MappingTarget Case entity);
}