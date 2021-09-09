package com.realcoderz.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.realcoderz.entity.JobProfile;

@Repository
public interface ProfileRepo  extends JpaRepository<JobProfile, Integer>{

	//--------------------------update interviewer --------------
	@Modifying(clearAutomatically = true)
	@Query("update JobProfile set interviwerNumber=:interviwerNumber , complete= :complete where profile=:profile and profileId=:profileId")
	public void updateRecored(@Param("interviwerNumber")  Integer interviwerNumber, @Param("complete") Integer complete,@Param("profile")  String profile, @Param("profileId") Integer profileId);

	@Query("SELECT distinct profile FROM JobProfile  ")
	public List<String> findProfileList();
}
