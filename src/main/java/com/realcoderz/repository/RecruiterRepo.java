package com.realcoderz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import com.realcoderz.entity.Recruiter;


public interface RecruiterRepo extends CrudRepository<Recruiter, Integer>,JpaRepository<Recruiter, Integer> {
	@Query("FROM Recruiter where recruiterCategory=:profile ")
	public List<Recruiter>  getRecruiterBasedOnProfile(@Param("profile") String profile);

	@Query("SELECT distinct recruiterEmail FROM Recruiter where recruiterName=:name ")
	public String  getRecruiterEmailByRecuterName(@Param("name") String name);

	@Query("SELECT distinct recruiterEmail FROM Recruiter where recruiterName=:name ")
	public List<Recruiter>  getRecuterByRecuterName(@Param("name") String name);

	

}