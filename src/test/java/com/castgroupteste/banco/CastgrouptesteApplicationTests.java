package com.castgroupteste.banco;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.castgroupteste.banco.dto.DadosClienteDTO;
import com.castgroupteste.banco.model.Cliente;
import com.castgroupteste.banco.repository.ClienteRepository;

import io.swagger.models.Response;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CastgrouptesteApplicationTests {
	
//	@Autowired
//	private TestRestTemplate testRestTemplate;
	@MockBean
	private ClienteRepository clienteRepository;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		
	}

	@Test
	@WithMockUser(username = "romano", password = "romano", roles = {"ADMIN"})
	void criarClienteBanco() {
		Cliente dadosClienteDTO = new Cliente();
		dadosClienteDTO.setNome("raphael");
		dadosClienteDTO.setCpf("08491498435");
		dadosClienteDTO.setTelefone("83 986776458");
		dadosClienteDTO.setIdade(24);
		dadosClienteDTO.setEmail("raphaeldobu@gmail.com");
		dadosClienteDTO.setDataNascimento("12/03/1996");
		
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(dadosClienteDTO);
		
		BDDMockito.when(clienteRepository.findAll()).thenReturn(clientes);
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/admin/clientes")).andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@WithMockUser(username = "romano", password = "romano")
	void criarClienteBancoNotAuthorized() {
		Cliente dadosClienteDTO = new Cliente();
		dadosClienteDTO.setNome("raphael");
		dadosClienteDTO.setCpf("08491498435");
		dadosClienteDTO.setTelefone("83 986776458");
		dadosClienteDTO.setIdade(24);
		dadosClienteDTO.setEmail("raphaeldobu@gmail.com");
		dadosClienteDTO.setDataNascimento("12/03/1996");
		
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(dadosClienteDTO);
		
		BDDMockito.when(clienteRepository.findAll()).thenReturn(clientes);
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/admin/clientes")).andExpect(MockMvcResultMatchers.status().isForbidden());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
//	@WithMockUser(username = "romano", password = "romano", roles = {"ADMIN", "USER"})
//	void deletarClienteBanco() {		
//		Cliente dadosClienteDTO = new Cliente();
//		dadosClienteDTO.setId(32323232L);
//		dadosClienteDTO.setNome("teste");
//		dadosClienteDTO.setCpf("645645653232");
//		dadosClienteDTO.setTelefone("83 65632323232");
//		dadosClienteDTO.setIdade(24);
//		dadosClienteDTO.setEmail("teste@gmail.com");
//		dadosClienteDTO.setDataNascimento("12/03/1966");
//		List<Cliente> clientes = new ArrayList<>();
//		clientes.add(dadosClienteDTO);
//		
////		BDDMockito.when(clienteRepository.save(dadosClienteDTO)).thenReturn(dadosClienteDTO);
//		BDDMockito.doNothing().when(clienteRepository).deleteById(32323232L);
//
//		try {
//			mockMvc.perform(MockMvcRequestBuilders.delete("/admin/cliente/{idCliente}", 32323232L)).andExpect(MockMvcResultMatchers.status().isOk());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
////		testRestTemplate = testRestTemplate.withBasicAuth("romano", "12345");
////		ResponseEntity<String> response = testRestTemplate.exchange("/admin/cliente/{idCliente}", HttpMethod.DELETE, null, String.class, 1L);
////		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
//	}

}
