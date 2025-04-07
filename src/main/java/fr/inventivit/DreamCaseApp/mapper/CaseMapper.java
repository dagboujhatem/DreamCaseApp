package fr.inventivit.DreamCaseApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import fr.inventivit.DreamCaseApp.dto.Case.request.CaseRequest;
import fr.inventivit.DreamCaseApp.dto.Case.response.CaseResponse;
import fr.inventivit.DreamCaseApp.models.Case;

@Mapper
public interface CaseMapper {
    CaseResponse toResponse(Case entity);
    Case toEntity(CaseRequest dto);
    void updateFromDto(CaseRequest dto, @MappingTarget Case entity);
}