package com.zigato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zigato.model.Address;

public interface AddressRepo extends JpaRepository<Address, Long>{

}
