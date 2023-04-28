package com.pale.clientrestproject.repository;

import com.pale.clientrestproject.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientRepo extends JpaRepository<Client, Long> {
    List<Client> findByFirstName(String firstName);
    Client findByIdNumber(String iDNumber);
    Client findByMobileNumber(String mobileNumber);
    List<Client> findByLastName(String lastName);
}
