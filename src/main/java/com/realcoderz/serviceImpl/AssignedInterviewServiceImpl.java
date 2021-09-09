package com.realcoderz.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.realcoderz.entity.AssignedInterview;
import com.realcoderz.repository.AssignedInterviewRepo;
import com.realcoderz.services.AssignedInterviewService;

@Service
public class AssignedInterviewServiceImpl implements AssignedInterviewService{
	
	@Autowired
	private AssignedInterviewRepo repo;
	
	@Value("${assigned}")
	private List<String> assign;

	/**
	 * @param map of AssignedInterview Details
	 * @return status
	 */
	
	@Override
	public Map assignedInterview(Map map) {
		Map<String, Object> resultSet = new HashMap<String, Object>();		
		try {
			if (map.keySet().containsAll(assign)) {
				if (map.values().stream().noneMatch(data -> data.toString().trim().isEmpty())) {
					AssignedInterview assign = new AssignedInterview();
					assign.setProfileId(Long.parseLong(map.get("profileId").toString()));
					assign.setInterviewerEmail(map.get("InterviewerEmail").toString());
					assign.setInterviewNumber(Long.parseLong((String) map.get("InterviewNumber")));
					assign.setPassingCriteria(Long.parseLong(map.get("PassingCriteria").toString()));
					assign.setInterviewDate(new SimpleDateFormat("dd-mm-yyyy").parse((String) map.get("InterviewDate")));
					if (assign != null) {
						assign = repo.save(assign);
						resultSet.put("Saved data : ", assign);
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
			resultSet.put("Exception", ex.getMessage());
		}
		return resultSet;
	}

}
