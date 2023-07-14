package com.testcodes.java;
import org.springframework.stereotype.Repository;

// @Repository
// public interface LeadRepository<Lead> extends JpaRepository<Lead, Integer> {
//     <Lead> Lead findByLeadId(Integer leadId);
// }

@Repository
public interface LeadRepository<Lead> extends JpaRepository<Lead, Integer> {
    Lead findByLeadId(Integer leadId);
    Lead findByMobileNumber(String mobileNumber);

    <Lead> Object save(Lead any);
}
