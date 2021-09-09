package com.realcoderz.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;
@Entity
@Table(name = "recruiting_managers ")
@Data
public class RecruiterManager {
	
	@Id
	@GenericGenerator(name = "recruiting_managers_seq",strategy = "sequence")
	@GeneratedValue(generator = "recruiting_managers_seq")
	@Column(name = "recruiting_manager_id")
	private Integer recuterManagerId;
	private Integer noOfInterview;
	private String experience;
	private String jobProfile;
	private String recruiterEmail;
	private String addProfile;
	private Integer adminId;
	
 
}
