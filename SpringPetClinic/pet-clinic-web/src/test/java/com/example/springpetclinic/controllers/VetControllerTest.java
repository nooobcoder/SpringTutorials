package com.example.springpetclinic.controllers;

import com.example.springpetclinic.model.Vet;
import com.example.springpetclinic.services.VetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = VetController.class)
class VetControllerTest {

	@MockBean
	VetService vetService;

	@Autowired
	MockMvc mockMvc;

	@Test
	void listVets() throws Exception {
		Vet vet1 = new Vet();
		vet1.setId(1L);
		vet1.setFirstName("John");
		vet1.setLastName("Doe");

		Vet vet2 = new Vet();
		vet2.setId(2L);
		vet2.setFirstName("Jane");
		vet2.setLastName("Doe");

		when(vetService.findAll()).thenReturn(Set.of(vet1, vet2));

		mockMvc.perform(get("/vets/index")).andExpect(status().isOk()).andExpect(view().name("vets/index"))
				.andExpect(model().attributeExists("vets")).andExpect(model().attribute("vets", hasSize(2)));
	}

	@Test
	void getVetsJson() throws Exception {
		Vet vet1 = new Vet();
		vet1.setId(1L);
		vet1.setFirstName("John");
		vet1.setLastName("Doe");

		Vet vet2 = new Vet();
		vet2.setId(2L);
		vet2.setFirstName("Jane");
		vet2.setLastName("Doe");

		when(vetService.findAll()).thenReturn(Set.of(vet1, vet2));

		mockMvc.perform(get("/api/vets")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(2)));
	}

}
