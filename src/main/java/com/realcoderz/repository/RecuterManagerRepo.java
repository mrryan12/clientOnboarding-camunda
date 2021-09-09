package com.realcoderz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.realcoderz.entity.RecruiterManager;


public interface RecuterManagerRepo extends CrudRepository<RecruiterManager, Integer>,JpaRepository<RecruiterManager, Integer> {
	@Query("FROM RecruiterManager")
	public List<RecruiterManager>  getAllClientDetails();

}
