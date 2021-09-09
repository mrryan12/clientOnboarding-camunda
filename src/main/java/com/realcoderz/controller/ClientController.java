package com.realcoderz.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.realcoderz.services.ClientService;
@RestController
public class ClientController  {

	@Autowired
	private ClientService clientService;
	
	
	/**
	 * @return All Client
	 */
	@PostMapping("/getAllClient")
	public Map<String ,Object> getAllClientDetails(){
		return clientService.getAllClientDetails();
	}
	
	

}