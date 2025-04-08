package fr.inventivit.DreamCaseApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.inventivit.DreamCaseApp.dto.Case.request.CaseRequest;
import fr.inventivit.DreamCaseApp.models.Case;
import fr.inventivit.DreamCaseApp.repository.CaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CaseControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private CaseRepository repository;

	@BeforeEach
	void setup() {
		repository.deleteAll();
	}

	@Test
	void shouldCreateAndGetCase() throws Exception {
		CaseRequest request = new CaseRequest();
		request.setTitle("Test Case");
		request.setDescription("Test Description");

		String json = objectMapper.writeValueAsString(request);
		String response = mockMvc.perform(post("/api/v1/cases")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.caseId").exists())
				.andReturn()
				.getResponse()
				.getContentAsString();
		Long createdId = objectMapper.readTree(response).get("caseId").asLong();

		mockMvc.perform(get("/api/v1/cases/{id}", createdId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.caseId").value(createdId));
	}

	@Test
	void shouldGetAllCases() throws Exception {
		Case case1 = new Case();
		case1.setTitle("Test Case");
		case1.setDescription("Test Description");
		case1.setCreationDate(LocalDateTime.now());
		case1.setLastUpdateDate(LocalDateTime.now());
		repository.save(case1);

		mockMvc.perform(get("/api/v1/cases"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(1));
	}

	@Test
	void shouldUpdateCase() throws Exception {
		Case existing = new Case();
		existing.setTitle("Test Case");
		existing.setDescription("Test Description");
		existing.setCreationDate(LocalDateTime.now());
		existing.setLastUpdateDate(LocalDateTime.now());
		existing = repository.save(existing);

		CaseRequest updated = new CaseRequest();

		mockMvc.perform(put("/api/v1/cases/{id}", existing.getCaseId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(updated)))
				.andExpect(status().isOk());
	}

	@Test
	void shouldDeleteCase() throws Exception {
		Case existing = new Case();
		existing.setTitle("Test Case");
		existing.setDescription("Test Description");
		existing.setCreationDate(LocalDateTime.now());
		existing.setLastUpdateDate(LocalDateTime.now());
		existing = repository.save(existing);

		mockMvc.perform(delete("/api/v1/cases/{id}", existing.getCaseId()))
				.andExpect(status().isOk());

		mockMvc.perform(get("/api/v1/cases/{id}", existing.getCaseId()))
				.andExpect(status().isOk())
				.andExpect(content().string("null"));
	}

}
