package com.macons.apparkme;

import com.macons.apparkme.controller.LoginController;
import com.macons.apparkme.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
@ExtendWith(SpringExtension.class)
class ApparkmeApplicationIntegrationTests {

	MockMvc mockMvc;

	@InjectMocks
	LoginController loginController;

	@MockBean
	TokenService tokenService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(this.loginController).build();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void WHEN_call_users_THEN_the_status_is_200()  {

		when(tokenService.getJWTToken("pepito")).thenReturn((String) "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhcHBhcmttZUpXVCIsInN1YiI6InBlcGl0byIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2ODA0MDE4MjQsImV4cCI6MTY4MDQwMjQyNH0.YbiNVYhXCnTJSSIzBKAt8QcUpugXx13O0_wnHoiOoGQ");

		try {
			mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/apparkme/api/v1/login?username=pepito&password=123456")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(content().json("{\"user\":\"pepito\",\"token\":\"Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhcHBhcmttZUpXVCIsInN1YiI6InBlcGl0byIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2ODA0MDE4MjQsImV4cCI6MTY4MDQwMjQyNH0.YbiNVYhXCnTJSSIzBKAt8QcUpugXx13O0_wnHoiOoGQ\"}"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
