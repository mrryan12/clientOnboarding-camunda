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
@Table(name = "Interviewer")
@Data
public class Interviewer {
	@Id
	@GenericGenerator(name = "interviewer_seq",strategy = "sequence")
	@GeneratedValue(generator = "interviewer_seq")
	@Column(name = "interviewerId")
	private Integer interviwerId;
	@NotNull(message = "Interviewer name must not null")
	private String interviwerName;
	@NotNull(message = "Interviewer category must not null")
	private String category;
	@NotNull(message = "Interviewer profile must not null")
	private String profile;
	@NotNull(message = "Interviewer email must not null")
	private String interviwerEmail;
	@NotNull(message = "Interviewer experience must not null")
	private Integer Experience;
}
