package fr.inventivit.DreamCaseApp.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import fr.inventivit.DreamCaseApp.service.CaseService;
import fr.inventivit.DreamCaseApp.dto.Case.request.CaseRequest;
import fr.inventivit.DreamCaseApp.dto.Case.response.CaseResponse;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/case")
public class CaseController {

    private final CaseService service;

    @GetMapping
    public List<CaseResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CaseResponse> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public CaseResponse create(@RequestBody CaseRequest request) {
        return service.save(request);
    }

    @PutMapping("/{id}")
    public CaseResponse update(
        @PathVariable Long id,
        @RequestBody CaseRequest request
    ) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}