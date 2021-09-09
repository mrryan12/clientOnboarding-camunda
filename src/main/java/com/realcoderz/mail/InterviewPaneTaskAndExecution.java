package com.realcoderz.mail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.TaskListener;

	public class InterviewPaneTaskAndExecution implements ExecutionListener ,TaskListener{
		@Override
		public void notify(DelegateExecution execution) throws Exception {
			String email= (String)execution.getVariable("recruiteremail");
			execution.setVariable("recruiteremail", email);
		}

		@Override
		public void notify(DelegateTask delegateTask) {
			String email= (String) delegateTask.getVariable("recruiteremail");
			if(email!=null) {
			//	SendMail.sendEmail(delegateTask,email,"interview");
			}
			else 
				System.out.println("email not found "+email);
			
		}
		
		

}
