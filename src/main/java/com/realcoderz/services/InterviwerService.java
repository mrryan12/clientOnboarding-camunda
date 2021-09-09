package com.realcoderz.services;

import java.util.Map;

import org.springframework.validation.BindingResult;

import com.realcoderz.entity.Interviewer;

public interface InterviwerService {
	
	public Map<String, Object>  getAllInterviwer(String profile,String category);
	
	public Map<String, Object>  getAllInterviwerDetails();
	
	public Map<String, Object> getAllInterviwerProfileBased(String category);

	public Map<String, Object> interviewSaveData(Map map);
	
	
	

}
