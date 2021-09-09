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
@Table(name = "client")
@Data
public class Client {

	@Id
	@GenericGenerator(name = "client_seq",strategy = "sequence" )
	@GeneratedValue(generator = "client_seq")
	@Column(name = "id")
	private Integer id;


	@NotNull
	@Column(name="name")
	private String name;

	@Column(name="email")
	private String email;

	@Column(name="licence")
	private String licence;

	@Column(name="description")
	private String description;

}