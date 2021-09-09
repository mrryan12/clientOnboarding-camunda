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
import com.realcoderz.services.InterviwerService;
@RestController("interviewerController")
@CrossOrigin("*")
public class InterviewerController {

	@Autowired
	private InterviwerService service;
	
	@Autowired
	private ObjectMapper mapper;

	/**
	 * 
	 * @param profile
	 * @return All Interviewer who belongs to profile
	 */
	@PostMapping("/getInterviewerNameProfileBased")
	public Map<String, Object> getAllInterviwerProfileBased(@RequestBody String profile) {
		Map<String, Object> resultSet = new HashMap<String, Object>();
		try {
			resultSet = service.getAllInterviwerProfileBased(EncryptDecryptUtils.Decrypt(profile));
		} catch (Exception ex) {
			resultSet.clear();
			resultSet.put("status", "exception");
		}
		return resultSet;
	}
	

	/**
	 * @return All Interviewers
	 */
	@PostMapping("/getInterviwerList")
	public Map<String, Object>  getInterviwerList() {
		return service.getAllInterviwerDetails();
	}


	/**
	 * 
	 * @param interviewer Details
	 * @param bresult for checking Validation
	 * @return
	 */
	@PostMapping(value = "/interviewSaveData")
	public Map<String, Object> interviewSaveData(@RequestBody String data){
		Map<String, Object> resultSet = new HashMap<String, Object>();
		try {
			resultSet = mapper.readValue(EncryptDecryptUtils.Decrypt(data), LinkedCaseInsensitiveMap.class);
			resultSet = service.interviewSaveData(resultSet);
		}catch(Exception ex){
			resultSet.clear();
			resultSet.put("status", "exception");
		}

		return resultSet;
	}
}
