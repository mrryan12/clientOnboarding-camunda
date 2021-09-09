package com.realcoderz.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.realcoderz.entity.Recruiter;
import com.realcoderz.repository.RecruiterRepo;
import com.realcoderz.services.RecruiterService;

@Service
public class RecuterServiceImpl implements RecruiterService {

	@Autowired
	private RecruiterRepo repo;

	
	/**
	 * @param map of recruiter details 
	 * @return status
	 */
	@Override
	public Map<String, Object> saveRecuterDetails(Map map) {
		Map<String, Object> resultSet = new HashMap<String, Object>();
		try {
			if (map.containsKey("recruiterName") && map.containsKey("recruiterCategory")
					&& map.containsKey("recruiterEmail")) {
				if (map.values().stream().noneMatch(data -> data.toString().trim().isEmpty())) {
					Recruiter recruiter = new Recruiter();
					recruiter.setRecruiterName(map.get("recruiterName").toString());
					recruiter.setRecruiterEmail(map.get("recruiterEmail").toString());
					recruiter.setRecruiterCategory(map.get("recruiterCategory").toString());
					if (recruiter != null) {
						recruiter = repo.save(recruiter);
						resultSet.put("status", "success");
						resultSet.put("Saved data : ", recruiter);
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
	
	
	/**
	 * 
	 * @param profile For getting Recruiter
	 * @return recruiter based on profile
	 */
	@Override
	public Map<String, Object> getRecruiterBasedOnProfile(String profile) {
		
		Map<String, Object> recruiterResult=new HashMap<String, Object>();
		try {
			if(profile!=null)
				repo.getRecruiterBasedOnProfile(profile).stream().forEach(data->recruiterResult.put("result", data.getRecruiterName()));
			else
				recruiterResult.put("status","profile is null");
		} catch (Exception e) {
			recruiterResult.put("status", "exception rise");
		}
		return recruiterResult;

	}
	
	
	

	/**
	 * @return All Recruiter
	 */
	@Override
	public Map<String, Object> getAllRecuter() {
		Map<String, Object> mapRecruiter=new HashMap<String, Object>();
		try {
			List<Recruiter> recruiter=repo.findAll();
			if(!recruiter.isEmpty()) {
				mapRecruiter.put("result",recruiter);
				mapRecruiter.put("status","Success");
			}else 
				mapRecruiter.put("status","No record found ");
		}catch (Exception e) {
			mapRecruiter.put("status","Exception rise ");
		}
		return mapRecruiter;
	}
	
	
	
	

	@Override
	public Map<String,Object> getRecuterByName(String name) {
		Map<String,Object> mapResult=new HashMap<String, Object>();
		if(name!=null) 
			try {
				repo.getRecuterByRecuterName(name).stream().forEach(data->mapResult.put("result",data));
				mapResult.put("status", "success");	
			} catch (Exception e) {
				mapResult.put("status", "failure");
			}
		else
			 mapResult.put("status", "name must not be null ");
		return mapResult;
	}



}