package com.ikcon.tech.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="notify_tab")
public class Notification {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name="notify_id")	
	private Long id;
	
	@Column(name="emailId")
	private String emailId;
	
	@Column(name="message")
	private String  message;
	
	@Column(name="moduleType")
	private String moduleType;
	
	@Column(name = "createdDateTime")
	private LocalDateTime createdDateTime;
	
	@Column(name = "modifiedDateTime")
	private LocalDateTime modifiedDateTime;
	
	@Column(name = "createdBy")
	private String createdBy;
	
	@Column(name = "modifiedBy")
	private String modifiedBy;
	
	@Column(name = "createdByEmailId")
	private String createdByEmailId;
	
	@Column(name = "modifiedByEmailId")
	private String modifiedByEmailId;

}
