package com.ikcon.tech.serviceImpl;



import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikcon.tech.dto.NotificationDto;
import com.ikcon.tech.entity.Notification;
import com.ikcon.tech.repo.NotificationRepo;
import com.ikcon.tech.service.NotificationService;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationServiceImpl  implements NotificationService{
	
	@Autowired
	private NotificationRepo notificationRepository;

	@Override
	public List<Notification> getAllNotifications(String emailId) {
		
		List<Notification> notificationList = notificationRepository.getAllNotifications(emailId);
		return notificationList;
	}

	@Override
	public Notification SaveNotification(Notification notification) {
		
		Notification saveNotification = notificationRepository.save(notification);
		return saveNotification;
	}
	

	

}
