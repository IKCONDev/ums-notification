package com.ikcon.tech.serviceImpl;



import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

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
		log.info("NotificationController.getAllNotification() entered with args:"+emailId);
		List<Notification> notificationList = notificationRepository.getAllNotifications(emailId);
		return notificationList;
	}

	@Transactional
	@Override
	public Notification saveNotification(Notification notification) {
		
		log.info("NotificationController.SaveNotification() entered with args:- notifaction");
		notification.setCreatedDateTime(LocalDateTime.now());
		Notification saveNotification = notificationRepository.save(notification);
		return saveNotification;
	}

	@Transactional
	@Override
	public List<Notification> createAllNotifications(List<Notification> notificationList) {
		return notificationRepository.saveAll(notificationList);
	}
	

	

}
