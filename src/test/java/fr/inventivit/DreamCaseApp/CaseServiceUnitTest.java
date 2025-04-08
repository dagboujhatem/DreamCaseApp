package fr.inventivit.DreamCaseApp;

import fr.inventivit.DreamCaseApp.dto.Case.request.CaseRequest;
import fr.inventivit.DreamCaseApp.dto.Case.response.CaseResponse;
import fr.inventivit.DreamCaseApp.mapper.CaseMapper;
import fr.inventivit.DreamCaseApp.models.Case;
import fr.inventivit.DreamCaseApp.repository.CaseRepository;
import fr.inventivit.DreamCaseApp.service.CaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CaseServiceUnitTest {

    @InjectMocks
    private CaseService service;

    @Mock
    private CaseRepository repository;

    @Mock
    private CaseMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Case> cases = List.of(new Case(), new Case());
        List<CaseResponse> responses = List.of(new CaseResponse(), new CaseResponse());

        when(repository.findAll()).thenReturn(cases);
        when(mapper.toResponse(any(Case.class))).thenReturn(new CaseResponse());

        List<CaseResponse> result = service.findAll();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void testFindById_found() {
        Long id = 1L;
        Case caseEntity = new Case();
        CaseResponse caseResponse = new CaseResponse();

        when(repository.findById(id)).thenReturn(Optional.of(caseEntity));
        when(mapper.toResponse(caseEntity)).thenReturn(caseResponse);

        Optional<CaseResponse> result = service.findById(id);

        assertTrue(result.isPresent());
        assertEquals(caseResponse, result.get());
    }

    @Test
    void testSave() {
        CaseRequest request = new CaseRequest();
        Case caseEntity = new Case();
        Case savedEntity = new Case();
        CaseResponse response = new CaseResponse();

        when(mapper.toEntity(request)).thenReturn(caseEntity);
        when(repository.save(caseEntity)).thenReturn(savedEntity);
        when(mapper.toResponse(savedEntity)).thenReturn(response);

        CaseResponse result = service.save(request);

        assertNotNull(result);
        verify(repository).save(any(Case.class));
    }

    @Test
    void testUpdate_success() {
        Long id = 1L;
        CaseRequest request = new CaseRequest();
        Case existingEntity = new Case();
        Case updatedEntity = new Case();
        CaseResponse response = new CaseResponse();

        when(repository.findById(id)).thenReturn(Optional.of(existingEntity));
        doNothing().when(mapper).updateFromDto(request, existingEntity);
        when(repository.save(existingEntity)).thenReturn(updatedEntity);
        when(mapper.toResponse(updatedEntity)).thenReturn(response);

        CaseResponse result = service.update(id, request);

        assertNotNull(result);
        verify(mapper).updateFromDto(request, existingEntity);
    }

    @Test
    void testUpdate_notFound() {
        Long id = 99L;
        CaseRequest request = new CaseRequest();

        when(repository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.update(id, request);
        });

        assertEquals("Case not found with id " + id, exception.getMessage());
    }

    @Test
    void testDelete() {
        Long id = 1L;

        doNothing().when(repository).deleteById(id);

        service.delete(id);

        verify(repository).deleteById(id);
    }
}
