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
	
	@Column(name="message")
	private String  message;
	
	@Column(name="moduleType")
	private String moduleType;
	
	@Column(name = "notificationTo")
	private String notificationTo;
	
	@Column(name="user_id")
	private String emailId;
	
	@Column(name = "createdDateTime")
	private LocalDateTime createdDateTime;
	
	@Column(name = "createdByEmailId")
	private String createdByEmailId;

}
