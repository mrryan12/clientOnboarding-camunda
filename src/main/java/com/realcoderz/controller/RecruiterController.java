package com.realcoderz.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realcoderz.encryption.EncryptDecryptUtils;
import com.realcoderz.services.RecruiterService;

@RestController
@RequestMapping(path = "/")
@CrossOrigin("*")
public class RecruiterController {
	
	@Autowired
	private RecruiterService recruiterservice;
	
	@Autowired
	private ObjectMapper mapper;


	/**
	 * @param recruiter data for save
	 * @return status
	 */
	@PostMapping("/saveRecuter")
	public Map<String, Object> saveRecuterDetails(@RequestBody String data) {
		Map<String, Object> resultSet = new HashMap<>();
		try {
			resultSet = mapper.readValue(EncryptDecryptUtils.Decrypt(data), LinkedCaseInsensitiveMap.class);
			resultSet = recruiterservice.saveRecuterDetails(resultSet);
		} catch (Exception ex) {
			resultSet.clear();
			resultSet.put("status", "Exception");
		}
		return resultSet;

	}



}