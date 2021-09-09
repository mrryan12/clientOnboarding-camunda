package com.realcoderz.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.realcoderz.entity.Interviewer;
import com.realcoderz.repository.InterviwerRepo;
import com.realcoderz.services.InterviwerService;
@Service("interviwerService")
public class InterviwerServiceImpl implements InterviwerService {
	@Autowired
	private InterviwerRepo repo;

	@Override
	public Map<String, Object> getAllInterviwer(String profile,String category){
		List<String> listInterviewer=new ArrayList<String>();
		Map<String, Object> mapResult=new HashMap<String, Object>();
		try {
			if(profile!=null && category!=null) {
				listInterviewer=repo.getAllInterviwer(profile, category);
				if(listInterviewer.isEmpty()) {
					mapResult.put("result",listInterviewer);
					mapResult.put("status","success");
				}else {
					mapResult.put("result","no record found ");
					mapResult.put("status","success");
				}
			}else {
				mapResult.put("status","success");
				mapResult.put("result","profile name and category must not be null");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mapResult;
	}

	
	
	
	

	/**
	 * @return All Interviewers
	 */
	@Override
	public Map<String, Object> getAllInterviwerDetails() {

		Map<String, Object> result=new HashMap<String, Object>();
		List listInterviewer=null;
		try {
			listInterviewer=repo.findAll();

			if(!listInterviewer.isEmpty()) {
				result.put("status","success");
				result.put("result", listInterviewer);
			}else
				result.put("status","failure");
				
		} catch (Exception e) {
			result.put("status","exception");
			
		}

		return result;
	}


	
	
	/**
	 * 
	 * @param profile
	 * @return All Interviewer who belongs to profile
	 */
	@Override
	public Map<String, Object> getAllInterviwerProfileBased(String profile) {
		Map<String, Object> result=new HashMap<String, Object>();

		if(profile!=null) 
			try {
				result.put("status","success");
				result.put("result", repo.getAllInterviwerProfileBased(profile));
			} catch (Exception e) {
				result.put("status","exception raised");
			}
		else
			result.put("status", "profile is null"); 
		return result;
	}
	
	
	
	
	/**
	 * @param map of interviewer values
	 * @return status
	 */
	@Override
	public Map<String, Object> interviewSaveData(Map map) {
		Map<String, Object> resultSet = new HashMap<String, Object>();
		try {
			if (map.containsKey("interviwerName") && map.containsKey("category") && map.containsKey("interviwerEmail")
					&& map.containsKey("experience") && map.containsKey("profile")) {
				if (map.values().stream().noneMatch(data -> data.toString().trim().isEmpty())) {
					Interviewer interviewer = new Interviewer();
					interviewer.setInterviwerName(map.get("interviwerName").toString());
					interviewer.setCategory(map.get("category").toString());
					interviewer.setInterviwerEmail(map.get("interviwerEmail").toString());
					interviewer.setExperience(Integer.parseInt(map.get("experience").toString()));
					interviewer.setProfile(map.get("profile").toString());
					if (interviewer != null) {
						interviewer = repo.save(interviewer);
						resultSet.put("Saved data : ", interviewer);
						resultSet.put("status", "success");

					}
				} else {
					resultSet.put("status", "Exception");
					resultSet.put("msg", "KEY Can't contain Null Value.");
					return resultSet;
				}
			} else {
				resultSet.put("status", "Exception");
				resultSet.put("msg", "Please provide valid KEY.");
				return resultSet;
			}
		} catch (Exception ex) {
			resultSet.put("status", "Oops! something went wrong");
		}
		return resultSet;
	}	
			
			
			
			
			
			
			
			
			
			
		
		
}