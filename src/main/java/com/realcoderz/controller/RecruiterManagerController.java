package com.realcoderz.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realcoderz.encryption.EncryptDecryptUtils;
import com.realcoderz.services.RecruiterService;

@RestController("RecuterManagerController")
@CrossOrigin("*")
public class RecruiterManagerController  {
	
	@Autowired
	private RecruiterService recservice;
	
	@Autowired
	private ObjectMapper mapper;

	/**
	 * @return All Recruiter
	 */
	@PostMapping("/getAllrecruiter")
	public Map<String, Object> getAllrecruiter() {
		return recservice.getAllRecuter();
	}

	/**
	 * 
	 * @param profile For getting Recruiter
	 * @return recruiter based on profile
	 */
	@PostMapping("/getRecruiter")
	public Map<String, Object> getSelectedRecruiter(@RequestBody String data) {
		Map<String, Object> resultSet = new HashMap<>();
		try {
			resultSet = recservice.getRecruiterBasedOnProfile(EncryptDecryptUtils.Decrypt(data));
		}catch(Exception ex) {
			resultSet.put("exception", "Oops! something went wrong");
		}
		return resultSet;
	}


}