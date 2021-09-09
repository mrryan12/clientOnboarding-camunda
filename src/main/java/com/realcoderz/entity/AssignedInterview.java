package com.realcoderz.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class AssignedInterview {

	@Id
	@GenericGenerator(name = "interviewe_seq",strategy = "sequence")
	@GeneratedValue(generator = "interviewe_seq")
	@Column(name="Id")
	private Long assignedInterviewId;
	private Long InterviewNumber;
	private String InterviewerEmail;
	private Long PassingCriteria;
    private Long profileId;
    @DateTimeFormat
    private Date InterviewDate;
}
