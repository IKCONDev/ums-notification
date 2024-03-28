package com.ikcon.tech.utils;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RoleVO {

	private Long roleId;
	
	private String roleName;
	
	private LocalDateTime createdDateTime;
	
	private LocalDateTime modifiedDateTime;
	
	private String createdBy;
	
	private String modifiedBy;
	
	private String createdByEmailId;
	
	private String modifiedByEmailId;
		
	private String roleStatus;
}
