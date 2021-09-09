package com.realcoderz.mail;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.TaskListener;
public class JobProfileTaskAndExecution implements ExecutionListener ,TaskListener{
	
	
	@Override
	public void notify(DelegateExecution execution) throws Exception {
		String email= (String)execution.getVariable("clientemail");
		execution.setVariable("clientemail", email);
		if(email==null) {
			execution.setVariable("clientemail", "arvind7238realcoderz@gmail.com");
		}
		
		 
	}

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("JobProfileTaskAndExecution.notify()");
		System.out.println(delegateTask.getId());
		String email= (String) delegateTask.getVariable("clientemail");
		if(email!=null) { 
		//	SendMail.sendEmail(delegateTask,email,"jobprofile");
		}
		else {
			System.out.println("email not found "+email);
		}
	}
	
	

}
