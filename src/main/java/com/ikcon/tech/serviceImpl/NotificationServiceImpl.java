package com.ikcon.tech.serviceImpl;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
		log.info("NotificationController.getAllNotification() entered with args : emailid - "+emailId);
		List<Notification> notificationList = notificationRepository.getAllNotifications(emailId);
		return notificationList;
	}

	@Transactional
	@Override
	public Notification saveNotification(Notification notification) {
		log.info("NotificationController.SaveNotification() entered with args : notification object");
		notification.setStatus("Unread");
		notification.setCreatedDateTime(LocalDateTime.now());
		notification.setCreatedByEmailId(notification.getEmailId());
		Notification saveNotification = notificationRepository.save(notification);
		return saveNotification;
	}

	@Transactional
	@Override
	public List<Notification> createAllNotifications(List<Notification> notificationList) {
		notificationList.forEach(notification -> {
			notification.setStatus("Unread");
		});
		return notificationRepository.saveAll(notificationList);
	}
	
	@Transactional
	@Override
	public Notification updateNotification(Notification notification) {
		log.info("NotificationController.SaveNotification() entered with args : notification object");
		Optional<Notification> optNotification = notificationRepository.findById(notification.getId());
		Notification dbNotification = null;
		if(optNotification.isPresent()) {
			dbNotification = optNotification.get();
		}
		dbNotification.setStatus("Read");
		return dbNotification;
	}
	
}
