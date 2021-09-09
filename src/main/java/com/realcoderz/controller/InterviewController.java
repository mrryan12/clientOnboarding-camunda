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
import com.realcoderz.services.AssignedInterviewService;

@RestController
@CrossOrigin("*")
public class InterviewController {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private AssignedInterviewService service; 
	
	
	/**
	 * 
	 * @param data of assignedInterview
	 * @return status
	 */
	@PostMapping("/assignInterview")
	public Map assignInterview(@RequestBody String data) {
		Map resultSet = new HashMap();
		try {
		resultSet =	objectMapper.readValue(EncryptDecryptUtils.Decrypt(data),LinkedCaseInsensitiveMap.class);
		resultSet = service.assignedInterview(resultSet);
		}catch(Exception ex) {
			resultSet.clear();
			resultSet.put("status", "exception");
			resultSet.put("Exception", ex.getMessage());
		}
		return resultSet;
	}
	

}
