package com.ikcon.tech.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ikcon.tech.Exception.ControllerException;
import com.ikcon.tech.Exception.EmptyInputException;
import com.ikcon.tech.Exception.ErrorCodeMessages;
import com.ikcon.tech.entity.Notification;
import com.ikcon.tech.service.NotificationService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/notification")
@Slf4j
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService; 
	
	
	@GetMapping("/all/{email}")
	public ResponseEntity<List<Notification>> getAllNotification(@PathVariable("email") String emailId){
		
	    log.info("getAllNotification() entered with args:"+emailId);
	    if(emailId == null || emailId.equals("")) {
	    	log.info("getAllNotification() EmptyInputException: email id is null");
	    	throw new EmptyInputException(ErrorCodeMessages.ERR_NOTIFICAT_ID_NOT_FOUND_CODE,
	    			ErrorCodeMessages.ERR_NOTIFICAT_ID_NOT_FOUND_MSG);
	    }
		try {
			  log.info("getAllNotification() is under execution...");
			  List<Notification> notificationList = notificationService.getAllNotifications(emailId);
			  log.info("getAllNotification() executed successfully");
			  return new ResponseEntity<>(notificationList,HttpStatus.OK);
			
		}catch (Exception e) {
			log.error("getAllNotification() exited with exception :Exception occured while fetching the Notification"+ e.getMessage(), e);
			throw new ControllerException(ErrorCodeMessages.ERR_NOTIFICAT_GET_ENITITY_UNSUCCESS_CODE, 
					ErrorCodeMessages.ERR_NOTIFICAT_GET_ENITITY_UNSUCCESS_MSG);
		}
	
	}
	
	@PostMapping("/create")
	public ResponseEntity<Notification> createNotificationDetails(@RequestBody Notification notification){
		log.info("saveNotificationDetails() entered with args notification:");
		if(notification == null) {
			log.info("saveNotificationDetails() EmptyInputException : object is null");
			throw new EmptyInputException(ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_IS_NULL_CODE,
					ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_IS_NULL_MSG);
		}
		try {
		   log.info("saveNotificationDetails() is under execution...");
		   Notification savedNotification = notificationService.saveNotification(notification);
		   log.info("saveNotificationDetails() executed successfully");
		   return new ResponseEntity<>(savedNotification,HttpStatus.CREATED);
			
		}catch (Exception e) {
			log.error("saveNotificationDetails() exited with exception : An Exception occurred while creating the Notification:"+e.getMessage(), e);
			throw new ControllerException(ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_SAVE_UNSUCCESS_CODE,
					ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_SAVE_UNSUCCESS_MSG);
		}
		
	}
	
	@PostMapping("/createAll")
	public ResponseEntity<List<Notification>> createAllNotifications(@RequestBody List<Notification> notificationList){
		log.info("createAllNotifications() entered with args:");
		if(notificationList.size() <= 0) {
			log.info("createAllNotifications() EmptyInputException: notification entity is null");
			throw new EmptyInputException(ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_LIST_IS_NULL_CODE,
					ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_LIST_IS_NULL_MSG);
		}
		log.info("createAllNotifications() is under execution...");
		try {
			List<Notification> createdNotificationList = notificationService.createAllNotifications(notificationList);
			log.info("createAllNotifications() executed succssfully");
			return new ResponseEntity<>(createdNotificationList, HttpStatus.CREATED);
		}catch(Exception e) {
			log.error("createAllNotifications() exited with exception :Exception occured while creating the Notification"+ e.getMessage(), e);
			throw new ControllerException(ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_SAVE_UNSUCCESS_CODE, 
					ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_SAVE_UNSUCCESS_MSG);
		}
		
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Notification> updateNotification(@RequestBody Notification notification){
		log.info("updateNotification() entered with args:"+notification);
		if(notification == null) {
			log.info("updateNotification() EmptyInputException: notification entity is null");
			throw new EmptyInputException(ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_IS_NULL_CODE,
					ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_IS_NULL_MSG);
		}
		log.info("updateNotification() is under execution...");
		try {
			Notification updatedNotification = notificationService.updateNotification(notification);
			log.info("updateNotification() executed succssfully");
			return new ResponseEntity<>(updatedNotification, HttpStatus.PARTIAL_CONTENT);
			
		}catch(Exception e) {
			log.error("updateNotification() exited with exception :Exception occured while updating the Notification"+ e.getMessage(), e);
			throw new ControllerException(ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_SAVE_UNSUCCESS_CODE, 
					ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_SAVE_UNSUCCESS_MSG);
			
		}
		
	}
	
	@GetMapping("/count/{emailId}")
	public ResponseEntity<Long> getAllNotificationCount(@PathVariable String emailId){
		
	    log.info("getAllNotificationCount() entered with args:"+emailId);
	    if(emailId == null || emailId.equals("")) {
	    	log.info("getAllNotificationCount() EmptyInputException: email id is null");
	    	throw new EmptyInputException(ErrorCodeMessages.ERR_NOTIFICAT_ID_NOT_FOUND_CODE,
	    			ErrorCodeMessages.ERR_NOTIFICAT_ID_NOT_FOUND_MSG);
	    }
		try {
			  log.info("getAllNotification() is under execution...");
			  Long notificationCount = notificationService.countUnreadNotificationsByEmailId(emailId);
			  log.info("getAllNotification() executed successfully");
			  return new ResponseEntity<>(notificationCount,HttpStatus.OK);
			
		}catch (Exception e) {
			log.error("getAllNotification() exited with exception :Exception occured while fetching the Notification"+ e.getMessage(), e);
			throw new ControllerException(ErrorCodeMessages.ERR_NOTIFICAT_GET_ENTITY_LIST_UNSUCCESS_CODE, 
					ErrorCodeMessages.ERR_NOTIFICAT_GET_ENTITY_LIST_UNSUCCESS_MSG);
		}
	
	}
	
	
	
}
