package com.realcoderz.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.stereotype.Service;

import com.realcoderz.entity.Client;
import com.realcoderz.mail.SendMail;
import com.realcoderz.repository.SuperAdminRepo;
import com.realcoderz.services.ClientService;
@Service
public class ClientServiceImpl implements ClientService,JavaDelegate ,TaskListener {
	
	@Autowired
	private SuperAdminRepo clientService;

	private static String email=null;

	//-------------------------save client details -----------------
	public void execute(DelegateExecution execution) throws Exception {
		
		String clientname=null;
		String license=null;
		Integer clientId=null;
		String description=null;
		email=(String)execution.getVariable("client-email");
		clientname=(String)execution.getVariable("client-name");
		license=(String)execution.getVariable("client-license");
		description=(String)execution.getVariable("description");
		Client client=new Client();
		client.setName(clientname);
		client.setEmail(email);
		client.setDescription(description);
		client.setLicence(license);

		if(email!=null && clientname!=null) {
			clientId =clientService.save(client).getId();
			SendMail.sendEmail(execution, email, "jobprofile");
		}
		if(clientId!=null) {
			execution.setVariable("adminId", clientId);
			execution.setVariable("clientemail", email);
		}
		return ;
	}

	@Override
	public void notify(DelegateTask delegateTask) {
		if(email!=null && delegateTask!=null)
			SendMail.sendEmail(delegateTask, email, "jobprofile");
	}


	
	/**
	 * @return All Client
	 */
	@Override
	public Map<String ,Object> getAllClientDetails() {
		Map<String ,Object>  clientMap=new HashMap<String, Object>();
		List listClient=new ArrayList<>();
		try {
			
			listClient=clientService.findAll();
			if(!listClient.isEmpty()) {
				clientMap.put("status", "success");
				clientMap.put("result", listClient);
			}
			else {
				clientMap.put("status","no record found ");
			}
		}
		catch (ConverterNotFoundException e) {
			e.printStackTrace();
			clientMap.put("status","Exception converter is not found  ");
		}
		catch (Exception e) {
			clientMap.put("status","exception");
			e.printStackTrace();
		}

		return clientMap;
	}
}
