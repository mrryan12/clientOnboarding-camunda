package com.realcoderz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
@Entity
@Table(name = "recruiter")
@Data
public class Recruiter {
	
	@Id
	@GenericGenerator(name = "recruiter_seq",strategy = "sequence")
	@GeneratedValue(generator = "recruiter_seq")
	@Column(name = "recruiter_id")
	private Integer recruiterId;
	@NotNull(message = "Recruiter name must not be null")
	private String recruiterName;
	@NotNull(message = "Recruiter category must not be null")
	private String recruiterCategory;
	@NotNull(message = "Recruiter email must not be null")
	private String recruiterEmail;
	

}
