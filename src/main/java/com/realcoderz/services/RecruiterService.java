package com.realcoderz.services;

import java.util.Map;


public interface RecruiterService {
	public Map<String, Object>  getRecruiterBasedOnProfile(String profile);
	public Map<String, Object> getAllRecuter();
	public Map<String,Object>  getRecuterByName(String name);
	public Map<String, Object> saveRecuterDetails(Map map);

}
