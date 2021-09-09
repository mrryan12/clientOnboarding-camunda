package com.realcoderz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.realcoderz.entity.Interviewer;


public interface InterviwerRepo extends JpaRepository<Interviewer, Integer> {
	@Query("SELECT interviwerName FROM Interviewer where profile=:profile and category =:category ")
	public List<String>  getAllInterviwer(@Param("profile" )String profile,@Param("category" )String category);

	@Query(" SELECT interviwerName FROM Interviewer where profile=:profile")
	public List<String>  getAllInterviwerProfileBased(@Param("profile") String profile);	
	
	

}
