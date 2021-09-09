package com.realcoderz.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.realcoderz.entity.JobProfile;
import com.realcoderz.mail.SendMail;
import com.realcoderz.repository.ProfileRepo;
import com.realcoderz.repository.RecruiterRepo;
import com.realcoderz.services.JobProfileService;

@Service("jobprofile")
public class JobProfileServiceImpl implements JobProfileService,JavaDelegate,TaskListener{
	@Autowired
	private ProfileRepo repo;

	@Autowired
	private RecruiterRepo recruiterRepo;
	
	
	@Value("${jobProfile}")
	private List<String> jobProfile;



	public static String taskId;
	public static String email;

	//------------save job profile---------------------------------
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String result=null;
		Integer interviwerNumber;
		Integer assigned;
		String profile1;
		String category;
		String recruiterName;
		String clientemail;
		String check;
		Integer adminId;

		JobProfile profile=new JobProfile();
		profile1=		(String)execution.getVariable("profile");
		category=		(String)execution.getVariable("category");
		recruiterName= (String)execution.getVariable("recruiter");
		interviwerNumber=		(Integer)execution.getVariable("noOfInterview");
		adminId=		(Integer)execution.getVariable("adminId");
		clientemail= (String)execution.getVariable("clientemail");

		if(recruiterName!=null) {
			try {
				email=recruiterRepo.getRecruiterEmailByRecuterName(recruiterName);
				execution.setVariable("recriuterEmail", email);
			}catch (Exception e) {

			}
		}
		if(category!=null && interviwerNumber!=null && profile1!=null && recruiterName!=null) {
			profile.setCategory(category);
			profile.setInterviwerNumber(interviwerNumber);
			profile.setProfile(profile1);
			profile.setRecuterName(recruiterName);
			profile.setComplete(0);
		}
		if(profile!=null && email==null) {
			result = repo.save(profile)!=null?"recoreed save":"recored not save";
			SendMail.sendEmail(execution, email, "Interview");
		}

		return ;
	}
	//--------------------------delegate task ----------------------
	
	
	@Override
	public void notify(DelegateTask delegateTask) {
		taskId=delegateTask.getId();
	}

	
	
	
	/**
	 * @return All Job Profiles
	 */
	
	@Override
	public Map<String, Object> getAllJobProfile() {
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {

			List<JobProfile> listOfJob= repo.findAll();
			if(!listOfJob.isEmpty()) {
				resultMap.put("result", listOfJob);
				resultMap.put("status", "success");
			}else {
				resultMap.put("result", "No record  found.");
				resultMap.put("status", "success");

			}
		} catch (Exception e) {
			resultMap.put("status", "Exception occured.");

		}
		return resultMap;
	}

	
	
	//--------------------count total no of job profile 
	@Override
	public long countJobProfile() {
		return repo.count();
	}

	//------------------------delete job profile based on specific job profile id.
	@Override
	public String delete(Integer id) {
		Optional<JobProfile> opt=	repo.findById(id);
		if(opt!=null) {
			repo.deleteById(id);
			return "Your Recored are deleted ";
		}
		else
			return "Your Recored are not deleted ";

	}



	@Override
	public Integer completeRound(Integer profileId) {
		Optional<JobProfile> profile= repo.findById(profileId);
		Integer id=null;
		if(profile!=null) {
			id=profile.get().getComplete();
		}

		return id;
	}


	/**
	 * @param Map of profile Object
	 * @return status
	 */
	@Override
	public Map<String, Object> saveProfile(Map map) {
		Map<String, Object> resultSet = new HashMap<String, Object>();
		try {
			if (map.keySet().containsAll(jobProfile)) {
				if (map.values().stream().noneMatch(data -> data.toString().trim().isEmpty())) {
					JobProfile job = new JobProfile();
					job.setProfile(map.get("profile").toString());
					job.setCategory(map.get("category").toString());
					job.setRecuterName(map.get("recuterName").toString());
					job.setInterviwerNumber(Integer.parseInt(map.get("interviwerNumber").toString()));
					if (job != null) {
						job = repo.save(job);
						resultSet.put("Saved data : ", job);
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