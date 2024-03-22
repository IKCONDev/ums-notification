package com.ikcon.tech.serviceImpl;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ikcon.tech.Exception.EmptyInputException;
import com.ikcon.tech.Exception.EmptyListException;
import com.ikcon.tech.Exception.ErrorCodeMessages;
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
		log.info("getAllNotification() entered with args : emailid - "+emailId);
		if(emailId == null || emailId.equals("")) {
			log.info("getAllNotification() EmptyInputException: email id is null");
			throw new EmptyInputException(ErrorCodeMessages.ERR_NOTIFICAT_EMAIL_ID_IS_NULL_CODE,
					 ErrorCodeMessages.ERR_NOTIFICAT_EMAIL_ID_IS_NULL_MSG);
		}
		log.info("getAllNotification() is under execution...");
		List<Notification> notificationList = notificationRepository.getAllNotifications(emailId);
		List<Notification> updatedList = new ArrayList<>();
		notificationList.forEach(notification ->{
			String email = notification.getCreatedByEmailId();
			UserVO user = null;
			try {
			    user =   restTemplate.getForObject("http://UMS-USERS-SERVICE/user/getUser/"+email,UserVO.class);
			    notification.setProfilepic(user.getProfilePic());
			}catch (Exception e) {
				user = new UserVO();
				notification.setProfilepic(null);
			}
			//System.out.println("the notification object is : "+notification);
			updatedList.add(notification);
		});
		log.info("getAllNotification() executed successfully");
		return updatedList;
	}

	@Transactional
	@Override
	public Notification saveNotification(Notification notification) {
		log.info("saveNotification() entered with args : notification object");
		if(notification == null) {
			log.info("saveNotification() EmptyInputException: notification entity is null");
			throw new EmptyInputException(ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_IS_NULL_CODE,
					ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_IS_NULL_MSG);
		}
		notification.setStatus("Unread");
		notification.setCreatedDateTime(LocalDateTime.now());
		notification.setCreatedByEmailId(notification.getEmailId());
		log.info("SaveNotification() is under execution...");
		Notification saveNotification = notificationRepository.save(notification);
		log.info("SaveNotification() executed successfully");
		return saveNotification;
	}

	@Transactional
	@Override
	public List<Notification> createAllNotifications(List<Notification> notificationList) {
		log.info("createAllNotifications() entered with args : notification object");
		if(notificationList.size() <=0) {
			log.info("createAllNotifications() EmptyInputException: notification entity is null");
			throw new EmptyListException(ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_LIST_IS_NULL_CODE, 
					ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_LIST_IS_NULL_MSG);
		}
		log.info("createAllNotifications() is under execution...");
		notificationList.forEach(notification -> {
			notification.setStatus("Unread");
		});
		List<Notification> savedNotificationList = notificationRepository.saveAll(notificationList);
		log.info("createAllNotifications()  executed successfully");
		return savedNotificationList;
	}
	
	@Transactional
	@Override
	public Notification updateNotification(Notification notification) {
		log.info("updateNotification() entered with args : notification object");
		if(notification == null) {
			log.info("updateNotification() EmptyInputException: notification entity is null");
			throw new EmptyInputException(ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_IS_NULL_CODE,
					ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_IS_NULL_MSG);
		}
		Optional<Notification> optNotification = notificationRepository.findById(notification.getId());
		log.info("updateNotification() is under execution...");
		Notification dbNotification = null;
		if(optNotification.isPresent()) {
			dbNotification = optNotification.get();
		}
		dbNotification.setStatus("Read");
		log.info("updateNotification() executed successfully");
		return dbNotification;
	}

	@Override
	public long countUnreadNotificationsByEmailId(String emailId) {
		log.info("countUnreadNotificationsByEmailId() entered with args :emailId - "+ emailId);
		if(emailId == null || emailId.equals("")) {
			log.info("countUnreadNotificationsByEmailId() EmptyInputException: email id is null");
			throw new EmptyInputException(ErrorCodeMessages.ERR_NOTIFICAT_EMAIL_ID_IS_NULL_CODE,
					 ErrorCodeMessages.ERR_NOTIFICAT_EMAIL_ID_IS_NULL_MSG);
		}
		log.info("countUnreadNotificationsByEmailId() is under execution...");
		long unreadNotifyCount=notificationRepository.countUnreadNotificationsByEmailId(emailId);
		log.info("countUnreadNotificationsByEmailId() executed sucessfully");
		return unreadNotifyCount;
	}
	
}
