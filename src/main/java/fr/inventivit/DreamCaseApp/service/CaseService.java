package fr.inventivit.DreamCaseApp.service;

import fr.inventivit.DreamCaseApp.dto.Case.request.CaseRequest;
import fr.inventivit.DreamCaseApp.dto.Case.response.CaseResponse;
import fr.inventivit.DreamCaseApp.mapper.CaseMapper;
import fr.inventivit.DreamCaseApp.models.Case;
import fr.inventivit.DreamCaseApp.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CaseService {

    @Autowired
    private CaseRepository repository;
    @Autowired
    private CaseMapper mapper;

    public List<CaseResponse> findAll() {
        return repository.findAll()
                         .stream()
                         .map(mapper::toResponse)
                         .collect(Collectors.toList());
    }

    public Optional<CaseResponse> findById(Long id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    public CaseResponse save(CaseRequest request) {
        Case entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    public CaseResponse update(Long id, CaseRequest request) {
        Case existingEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Case not found with id " + id));
        
        mapper.updateFromDto(request, existingEntity);
        
        Case savedEntity = repository.save(existingEntity);
        return mapper.toResponse(savedEntity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}