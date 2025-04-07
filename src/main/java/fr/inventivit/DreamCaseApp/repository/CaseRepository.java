package fr.inventivit.DreamCaseApp.repository;

import fr.inventivit.DreamCaseApp.models.Case;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<Case, Long> {
}