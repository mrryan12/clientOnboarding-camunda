package com.realcoderz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.realcoderz.entity.Client;

public interface SuperAdminRepo extends JpaRepository<Client, Integer> {

	
	
}
