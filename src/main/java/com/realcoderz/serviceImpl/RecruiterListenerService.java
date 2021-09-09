package com.realcoderz.serviceImpl;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.TaskListener;

import com.realcoderz.mail.SendMail;
public class RecruiterListenerService implements TaskListener,JavaDelegate{

	private static String recruiterEmail;
	private static String taskId;
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		recruiterEmail =(String)execution.getVariable("recriuterEmail");		
	}
	@Override
	public void notify(DelegateTask delegateTask) {
		taskId=delegateTask.getId();
			SendMail.sendEmail(delegateTask, recruiterEmail, "interview");
	}
	
	
}
