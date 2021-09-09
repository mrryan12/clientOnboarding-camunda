package com.realcoderz.repository;


import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.data.repository.CrudRepository;

import com.realcoderz.entity.Client;

public interface ClientRepo extends JpaRepository<Client,Integer>{

}
