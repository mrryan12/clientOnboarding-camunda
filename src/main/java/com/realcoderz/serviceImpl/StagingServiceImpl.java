package com.realcoderz.serviceImpl;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import com.realcoderz.services.JobProfileService;


public class StagingServiceImpl implements JavaDelegate {
	public static  int round=0;
	public boolean isInsert=false;
	String profile;
	
	@Autowired
	private  JobProfileService jobservice;
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		round =(Integer)execution.getVariable("number");//1
		profile =(String)execution.getVariable("profile1");//JAVA
		Integer profileid =(Integer)execution.getVariable("profileid");//31
		Integer noOfInterview=(Integer)execution.getVariable("noOfInterview");//2
		
		if(noOfInterview==(jobservice.completeRound(profileid))+1) {
			execution.setVariable("result", 0);
			jobservice.delete(profileid)
;			round=0;
		}else {
			execution.setVariable("result", 1);
			execution.setVariable("noOfInterview",noOfInterview);
		//	jobservice.updateRecored(noOfInterview, round, profile, profileid);
		}
	} 
	
}
