package io.github.mqdev.gestao_vagas.modules.company.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import io.github.mqdev.gestao_vagas.modules.company.dto.CreateJobDTO;
import io.github.mqdev.gestao_vagas.modules.company.entities.CompanyEntity;
import io.github.mqdev.gestao_vagas.modules.company.repositories.CompanyRepository;
import io.github.mqdev.gestao_vagas.modules.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

        private MockMvc mockMvc;

        @Autowired
        private WebApplicationContext webApplicationContext;

        @Autowired
        private CompanyRepository companyRepository;

        @Before
        public void setup() {
                mockMvc = MockMvcBuilders
                                .webAppContextSetup(webApplicationContext)
                                .apply(SecurityMockMvcConfigurers.springSecurity())
                                .build();
        }

        @Test
        public void shouldBeAbleToCreateAJob() throws Exception {

                var company = companyRepository.saveAndFlush(CompanyEntity.builder()
                                .name("NAME_TEST")
                                .email("EMAIL@TEST.COM")
                                .username("USERNAME_TEST")
                                .password("PASSWORD_TEST")
                                .description("DESCRIPTION_TEST")
                                .website("WEBSITE_TEST")
                                .build());

                var companyId = company.getId();

                var createdJobDTO = CreateJobDTO.builder()
                                .title("TITLE_TEST")
                                .description("DESCRIPTION_TEST")
                                .salary("SALARY_TEST")
                                .level("LEVEL_TEST")
                                .location("LOCATION_TEST")
                                .benefits("BENEFITS_TEST")
                                .requirements("REQUIREMENTS_TEST")
                                .build();

                var result = mockMvc.perform(MockMvcRequestBuilders.post("/company/job/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtils.objectToJsonString(createdJobDTO))
                                .header("Authorization", TestUtils.generateToken(null, companyId))
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk());

                System.out.println("Teste de criação de vaga realizado com sucesso! " + result);
        }

        @Test
        public void shouldNotBeAbleToCreateAJobWithoutACompany() throws Exception {

                var createdJobDTO = CreateJobDTO.builder()
                                .title("TITLE_TEST")
                                .description("DESCRIPTION_TEST")
                                .salary("SALARY_TEST")
                                .level("LEVEL_TEST")
                                .location("LOCATION_TEST")
                                .benefits("BENEFITS_TEST")
                                .requirements("REQUIREMENTS_TEST")
                                .build();

                var result = mockMvc.perform(MockMvcRequestBuilders.post("/company/job/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtils.objectToJsonString(createdJobDTO))
                                .header("Authorization", TestUtils.generateToken(null, UUID.randomUUID())))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());

                System.out.println("Teste de criação de vaga sem empresa realizado com sucesso! " + result);
        }

}
