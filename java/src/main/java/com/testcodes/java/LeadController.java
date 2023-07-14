package com.testcodes.java;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leads")
public class LeadController {
    private final LeadRepository leadRepository;

    @Autowired
    public LeadController(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    @PostMapping
    public ResponseEntity<?> createLead(@Validated @RequestBody Lead lead) {
        Integer leadId = lead.getLeadId();
        if (leadRepository.findByLeadId(leadId) != null) {
            ErrorResponse errorResponse = new ErrorResponse("E10010",
                    "Lead Already Exists in the database with the lead id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        leadRepository.save(lead);
        return ResponseEntity.ok(new SuccessResponse("Created Leads Successfully"));
    }

    @GetMapping("/mobileNumber/{mobileNumber}")
    public ResponseEntity<?> getLeadsByMobileNumber(@PathVariable String mobileNumber) {
        List<Lead> leads = leadRepository.findByMobileNumber(mobileNumber);

        if (leads.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse("E10011",
                    "No Lead found with the Mobile Number " + mobileNumber);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(new SuccessResponse(leads));
    }
}
