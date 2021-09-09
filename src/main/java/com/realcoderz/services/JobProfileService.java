package com.realcoderz.services;

import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;

public interface JobProfileService {
	public Map<String, Object>  getAllJobProfile();
	
	
	public long countJobProfile();
	public String delete(Integer id);
    public Integer completeRound(Integer profileId);
    
    public Map saveProfile(Map map);
	
}
