package com.ikcon.tech.utils;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class UserVO{
	
	
	private String email;
	
	private String encryptedPassword;
	
	private String previousPassword1;
	
	private String previousPassword2;
	
	private String previousPassword;
	
	private Set<RoleVO> userRoles = new HashSet<>();
	
	private int otpCode;
	
	private boolean twoFactorAuthentication;	
	
	private byte[] profilePic;
	
	private boolean isActive;
	
	private LocalDateTime createdDateTime;
	
	private LocalDateTime modifiedDateTime;
	
	private String createdBy;
	
	private String modifiedBy;
	
	private String createdByEmailId;
	
	private String modifiedByEmailId;
		
	
	

	
	
}





