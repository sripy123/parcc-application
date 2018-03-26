package com.nj.parcc;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.nj.parcc.dto.ParccResultDTO;
import com.nj.parcc.exception.ErrorMessages;
import com.nj.parcc.service.ParccService;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = ParccApplication.class)
@SpringBootTest
@EnableAutoConfiguration
@AutoConfigureMockMvc
public class ParccControllerTest {

	@Autowired
	private MockMvc _mvc;

	private HttpHeaders _httpHeaders;

	@MockBean
	private ParccService parccService;

	@Before
	public void setUp() {
		_httpHeaders = new HttpHeaders();
		 MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_GetAllParccResults() throws Exception {
		when(parccService.getParccResults()).thenReturn(returnParccResulsts());
		
		_mvc.perform(MockMvcRequestBuilders.get("/parcc/v1").headers(_httpHeaders).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void test_GetParccResultsById() throws Exception {
		when(parccService.getParccResults(anyLong())).thenReturn(returnParccResulst());
		
		ResultActions rsa = _mvc.perform(MockMvcRequestBuilders.get("/parcc/v1/1").headers(_httpHeaders).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		String response = rsa.andReturn().getResponse().getContentAsString();
		assertThat(response, containsString("\"id\":\"1\""));
	}
	
	@Test
	public void test_CreateParccResult() throws Exception {
		when(parccService.createParccResult(anyObject())).thenReturn(returnParccResulst());
		
		ResultActions rsa = _mvc.perform(MockMvcRequestBuilders.post("/parcc/v1")
				.headers(_httpHeaders)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{\r\n" + 
						"       \r\n" + 
						"        \"name\": \"ccc\",\r\n" + 
						"        \"school\": \"NJ Public school\",\r\n" + 
						"        \"marks\": [\r\n" + 
						"            {\r\n" + 
						"                \"sub\": \"MATH\",\r\n" + 
						"                \"mark\": \"11\"\r\n" + 
						"            }\r\n" + 
						"        ]\r\n" + 
						"    }"))
				.andExpect(status().isOk());
		String response = rsa.andReturn().getResponse().getContentAsString();
		System.out.println(response);
		assertThat(response, containsString("PARCC_001"));
	}
	
	@Test
	public void test_updateParccResult() throws Exception {
		when(parccService.updateParccResult(anyObject())).thenReturn(returnParccResulst());
		
		ResultActions rsa = _mvc.perform(MockMvcRequestBuilders.put("/parcc/v1")
				.headers(_httpHeaders)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{\r\n" + 
						"       \r\n" + 
						"        \"name\": \"ccc\",\r\n" + 
						"        \"school\": \"NJ Public school\",\r\n" + 
						"        \"marks\": [\r\n" + 
						"            {\r\n" + 
						"                \"sub\": \"MATH\",\r\n" + 
						"                \"mark\": \"11\"\r\n" + 
						"            }\r\n" + 
						"        ]\r\n" + 
						"    }"))
				.andExpect(status().isOk());
		String response = rsa.andReturn().getResponse().getContentAsString();
		System.out.println(response);
		assertThat(response, containsString("PARCC_002"));
	}
	
	@Test
	public void test_deleteParccResult() throws Exception {
		doNothing().when(parccService).deleteParccResult(anyLong());
		ResultActions rsa = _mvc.perform(MockMvcRequestBuilders.delete("/parcc/v1/1")
				.headers(_httpHeaders)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
		String response = rsa.andReturn().getResponse().getContentAsString();
		System.out.println(response);
		assertThat(response, containsString("PARCC_004"));
	}
	
	private List<ParccResultDTO> returnParccResulsts() {
		List<ParccResultDTO> list = new ArrayList<ParccResultDTO>();
		ParccResultDTO dto = new ParccResultDTO();
		dto.setId("1");
		dto.setMsg("success");
		dto.setName("dummy");
		dto.setSchool("trial");
		list.add(dto);
		return list;
	}
	
	private ParccResultDTO returnParccResulst() {
		ParccResultDTO dto = new ParccResultDTO();
		dto.setId("1");
		dto.setMsg("success");
		dto.setName("dummy");
		dto.setSchool("trial");
		return dto;
	}
	

	private ParccResultDTO createParccResulst() {
		ParccResultDTO dto = returnParccResulst();
		dto.setCode(ErrorMessages.PARCC_000.getCode());
		dto.setMsg(ErrorMessages.PARCC_000.getDefaultMessage());
		return dto;
	}


}