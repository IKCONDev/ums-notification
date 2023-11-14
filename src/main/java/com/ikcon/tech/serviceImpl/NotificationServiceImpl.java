package com.ikcon.tech.serviceImpl;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ikcon.tech.entity.Notification;
import com.ikcon.tech.repo.NotificationRepo;
import com.ikcon.tech.service.NotificationService;
import com.ikcon.tech.utils.UserVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationServiceImpl  implements NotificationService{
	
	@Autowired
	private NotificationRepo notificationRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Notification> getAllNotifications(String emailId) {
		log.info("NotificationController.getAllNotification() entered with args : emailid - "+emailId);
		List<Notification> notificationList = notificationRepository.getAllNotifications(emailId);
		List<Notification> updatedList = new ArrayList<>();
		notificationList.forEach(notification ->{
			String email = notification.getEmailId();
			UserVO user =   restTemplate.getForObject("http://UMS-USERS-SERVICE/user/getUser/"+email,UserVO.class);
			notification.setProfilepic(user.getProfilePic());
			System.out.println("the notification object is : "+notification);
			updatedList.add(notification);
		});
		return updatedList;
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
