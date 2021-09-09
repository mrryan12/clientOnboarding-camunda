package com.realcoderz.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realcoderz.entity.RecruiterManager;
import com.realcoderz.repository.RecuterManagerRepo;
import com.realcoderz.services.RecrutierManagerService;
@Service("recutermanager")
public class RecruiterManagerServiceImpl implements RecrutierManagerService {

	@Autowired
	private RecuterManagerRepo service;
	
	
	
	
	@Override
	public List<RecruiterManager> getAllRecuterManagerDetails() {
		return service.getAllClientDetails();
	}




	

	

}
