package fr.inventivit.DreamCaseApp.dto.Case.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaseResponse {
    private Long caseId;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    private String title;
    private String description;
}