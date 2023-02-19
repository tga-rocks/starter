package com.tga.starter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void noParamShouldReturnDefaultMessage() throws Exception {

		this.mockMvc.perform(get("/data")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.payload").value("some API response: somePayload"));
	}

	@Test
	public void paramShouldReturnCustomMessage() throws Exception {

		this.mockMvc.perform(get("/data").param("payload", "myPayload"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.payload").value("some API response: myPayload"));
	}

}
