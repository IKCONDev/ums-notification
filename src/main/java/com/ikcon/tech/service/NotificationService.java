package com.ikcon.tech.service;

import java.util.List;

import com.ikcon.tech.dto.NotificationDto;
import com.ikcon.tech.entity.Notification;


public interface NotificationService {

	List<Notification> getAllNotifications(String emailId);
	
    Notification SaveNotification(Notification notification);
	
}
