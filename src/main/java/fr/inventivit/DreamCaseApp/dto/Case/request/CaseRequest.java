package fr.inventivit.DreamCaseApp.dto.Case.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaseRequest {
    private String title;
    private String description;
}