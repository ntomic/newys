package hr.five.newys;

import hr.five.newys.controller.NewysController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class NewysApplicationTests {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private NewysController newysController;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void contextLoads() {
		assertThat(newysController).isNotNull();
	}
	
	@Test
	void unautorizedAccessRedirectsToLogin() {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/articles",
		                                          String.class)).contains("Sign in");
	}
	
	@Test
	public void homePageShouldReturnDefaultMessage() {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/index",
		                                          String.class)).contains("Welcome to NEWYS application");
	}
	
	@Test
	@WithMockUser(value = "user", password = "pass")
	public void deleteValidArticleShouldRedirect() throws Exception {
		mockMvc.perform(get("/article/delete/1"))
		       .andExpect(status().isFound());
	}
	
	@Test
	@WithMockUser(value = "user", password = "pass")
	public void deleteInvalidArticleShouldBeBadRequest() throws Exception {
		mockMvc.perform(get("/article/delete/11111"))
		.andExpect(status().isBadRequest())
		.andExpect(content().string(containsString("No article record exist for given id: 11111")));
	}
	
}
