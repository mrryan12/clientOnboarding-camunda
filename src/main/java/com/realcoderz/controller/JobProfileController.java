package com.realcoderz.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realcoderz.encryption.EncryptDecryptUtils;
import com.realcoderz.services.JobProfileService;

@RestController("jobprofilecontroller")
@CrossOrigin("*")
public class JobProfileController {
	
	@Autowired
	private JobProfileService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	/**
	 * @return All Job Profiles
	 */
	@PostMapping("/getAllJobProfile")
	public Map<String, Object> getAllJobProfile(){
		return service.getAllJobProfile();
	}	
	
	
	/** 
	 * @param Incrypted data of a Job profile
	 * @return
	 */
	@PostMapping("/saveProfile")
	public Map saveProfile(@RequestBody String data) {
		Map resultSet = new HashMap();
		try {
		    resultSet  = objectMapper.readValue(EncryptDecryptUtils.Decrypt(data),LinkedCaseInsensitiveMap.class);
		    service.saveProfile(resultSet);
		}catch(Exception ex) {
			resultSet.clear();
			resultSet.put("status", "exception");
		}
		return resultSet;
	}
	
	
	
}
