package fr.inventivit.DreamCaseApp.dto.Case.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaseRequest {
    private Long caseId;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    private String title;
    private String description;
}