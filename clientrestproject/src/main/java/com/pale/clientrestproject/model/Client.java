package com.pale.clientrestproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "tbl_client")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id", nullable = false)
    private Long id;
    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First Name cannot be empty or null")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last Name cannot be empty or null")
    private String lastName;
    @Column(name = "mobile_number", unique = true)
    @Pattern(regexp = "^\\d{10}$", message = "mobile number needs to be numeric and not empty")
    private String mobileNumber;
    @Column(name = "id_number", nullable = false, unique = true, length = 13)
    @Pattern(regexp = "^\\d{13}$", message = "ID number needs to be numeric and 13 characters")
    private String idNumber;
    @Column(name = "physical_address", nullable = false, length = 100)
    private String physicalAddress;

}
