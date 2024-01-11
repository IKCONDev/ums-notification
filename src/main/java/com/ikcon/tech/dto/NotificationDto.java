package com.ikcon.tech.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NotificationDto {
	
	private Long id;
	private String userId;
	private String message;
	private String moduleType;
	private LocalDateTime date;
	
	

}
