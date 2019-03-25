package acu.assignment2;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import acu.project1.Assignment2Application;
import acu.project1.persistence.entities.Teacher;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Assignment2Application.class)
@WebAppConfiguration
public class IntegrationTests {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	@Before
	public void setupMock() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void shouldGetAdministrators() throws UnsupportedEncodingException, Exception {
		List<Teacher> administrators = GetAdmins.getAdminstrators();
		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		String jsonInString = mapper.writeValueAsString(administrators);
		mockMvc.perform(get("/administrator/all"))
		.andExpect(status().isOk())
        .andExpect(content().string(jsonInString))
        .andReturn().getResponse().getContentAsString();
		
	}
}
