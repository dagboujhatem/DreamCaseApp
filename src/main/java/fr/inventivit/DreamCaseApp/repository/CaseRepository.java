package fr.inventivit.DreamCaseApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.inventivit.DreamCaseApp.models.Case;

public interface CaseRepository extends JpaRepository<Case, Long> {
}