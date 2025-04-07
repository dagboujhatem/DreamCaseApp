package fr.inventivit.DreamCaseApp.mapper;

import fr.inventivit.DreamCaseApp.dto.Case.request.CaseRequest;
import fr.inventivit.DreamCaseApp.dto.Case.response.CaseResponse;
import fr.inventivit.DreamCaseApp.models.Case;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CaseMapper {
    CaseResponse toResponse(Case entity);
    Case toEntity(CaseRequest dto);
    void updateFromDto(CaseRequest dto, @MappingTarget Case entity);
}