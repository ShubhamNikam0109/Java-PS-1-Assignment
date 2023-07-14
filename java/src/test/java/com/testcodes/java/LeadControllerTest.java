package com.testcodes.java;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LeadController.class)
public class LeadControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LeadRepository leadRepository;

    @InjectMocks
    private LeadController leadController;

    @Test
    public void testCreateLead_Success() throws Exception {
        Lead lead = new Lead();
        lead.setLeadId(5678);
        lead.setFirstName("Vineet");
        lead.setLastName("KV");
        lead.setMobileNumber("8877887788");
        lead.setGender("Male");
        lead.setDob(new Date());
        lead.setEmail("v@gmail.com");

        when(leadRepository.findByLeadId(5678)).thenReturn(null);
        when(leadRepository.save(any(Lead.class))).thenReturn(lead);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/leads")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"leadId\": 5678, \"firstName\": \"Vineet\", \"lastName\": \"KV\", \"mobileNumber\": \"8877887788\", \"gender\": \"Male\", \"dob\": \"2022-01-01\", \"email\": \"v@gmail.com\" }"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("success"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("Created Leads Successfully"));
    }

    @Test
    public void testCreateLead_LeadIdAlreadyExists() throws Exception {
        Lead lead = new Lead();
        lead.setLeadId(5678);

        when(leadRepository.findByLeadId(5678)).thenReturn(lead);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/leads")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"leadId\": 5678, \"firstName\": \"Vineet\", \"lastName\": \"KV\", \"mobileNumber\": \"8877887788\", \"gender\": \"Male\", \"dob\": \"2022-01-01\", \"email\": \"v@gmail.com\" }"))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("error"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorResponse.code").value("E10010"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorResponse.messages[0]").value("Lead Already Exists in the database with the lead id"));
    }

    @Test
    public void testGetLeadsByMobileNumber_NoLeadsFound() throws Exception {
        when(leadRepository.findByMobileNumber(anyString())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/leads/mobileNumber/8877887788")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("error"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorResponse.code").value("E10011"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorResponse.messages[0]").value("No Lead found with the Mobile Number 8877887788"));
    }
}

