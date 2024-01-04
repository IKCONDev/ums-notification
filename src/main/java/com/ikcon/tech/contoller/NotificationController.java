package com.ikcon.tech.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ikcon.tech.Exception.ControllerException;
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
	public ResponseEntity<?> getAllNotification(@PathVariable("email") String emailId){
		
	    log.info("NotificationController.getAllNotification() entered with args:"+emailId);
		try {
			  log.info("NotificationController.getAllNotification() is under execution...");
			  List<Notification> notificationList = notificationService.getAllNotifications(emailId);
			  log.info("NotificationController.getAllNotification() executed successfully");
			  return new ResponseEntity<>(notificationList,HttpStatus.OK);
			
		}catch (Exception e) {
			// TODO: handle exception
			log.info("NotificationController.getAllNotification() exited with exception :Exception occured while fetching the Notification"+ e.getMessage());
			e.printStackTrace();
			throw new ControllerException(ErrorCodeMessages.ERR_NOTIFICAT_LISt_IS_NULL_CODE, ErrorCodeMessages.ERR_NOTIFICAT_LISt_IS_NULL_MSG);
		}
	
	}
	
	@PostMapping("/create")
	public ResponseEntity<Notification> createNotificationDetails(@RequestBody Notification notification){
		log.info("NotificationController.saveNotificationDetails() entered with args notification:");
		try {
		   log.info("NotificationController.saveNotificationDetails() is under execution...");
		   Notification savedNotification = notificationService.saveNotification(notification);
		   log.info("NotificationController.saveNotificationDetails() executed successfully");
		   return new ResponseEntity<>(savedNotification,HttpStatus.CREATED);
			
		}catch (Exception e) {
			log.info("NotificationController.saveNotificationDetails() exited with exception : An Exception occurred while creating the Notification:"+e.getMessage());
			e.printStackTrace();
			//return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			throw new ControllerException(ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_IS_NULL_CODE, ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_IS_NULL_MSG);
		}
		
	}
	
	@PostMapping("/createAll")
	public ResponseEntity<List<Notification>> createAllNotifications(@RequestBody List<Notification> notificationList){
		log.info("createAllNotifications() entered with args:");
		log.info("createAllNotifications() is under execution...");
		try {
			List<Notification> createdNotificationList = notificationService.createAllNotifications(notificationList);
			log.info("createAllNotifications() executed succssfully");
			return new ResponseEntity<>(createdNotificationList, HttpStatus.CREATED);
		}catch(Exception e) {
			log.info("createAllNotifications() exited with exception :Exception occured while creating the Notification"+ e.getMessage());
			e.printStackTrace();
			//return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			throw new ControllerException(ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_IS_NULL_CODE, ErrorCodeMessages.ERR_NOTIFICAT_ENTITY_IS_NULL_MSG);
		}
		
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Notification> updateNotification(@RequestBody Notification notification){
		log.info("updateNotification() entered with args:"+notification);
		log.info("updateNotification() is under execution...");
		try {
			Notification updatedNotification = notificationService.updateNotification(notification);
			log.info("updateNotification() executed succssfully");
			return new ResponseEntity<>(updatedNotification, HttpStatus.PARTIAL_CONTENT);
			
		}catch(Exception e) {
			log.info("updateNotification() exited with exception :Exception occured while updating the Notification"+ e.getMessage());
			e.printStackTrace();
			throw new ControllerException(ErrorCodeMessages.ERR_NOTIFICAT_ID_NOT_FOUND_CODE, ErrorCodeMessages.ERR_NOTIFICAT_ID_NOT_FOUND_MSG);
			
		}
		
	}
	
	@GetMapping("/count/{emailId}")
	public ResponseEntity<Long> getAllNotificationCount(@PathVariable String emailId){
		
	    log.info("NotificationController.getAllNotificationCount() entered with args:"+emailId);
		try {
			  log.info("NotificationController.getAllNotification() is under execution...");
			  Long notificationCount = notificationService.countUnreadNotificationsByEmailId(emailId);
			  log.info("NotificationController.getAllNotification() executed successfully");
			  return new ResponseEntity<>(notificationCount,HttpStatus.OK);
			
		}catch (Exception e) {
			// TODO: handle exception
			log.info("NotificationController.getAllNotification() exited with exception :Exception occured while fetching the Notification"+ e.getMessage());
			e.printStackTrace();
			//return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			throw new ControllerException(ErrorCodeMessages.ERR_NOTIFICAT_LISt_IS_NULL_CODE, ErrorCodeMessages.ERR_NOTIFICAT_LISt_IS_NULL_MSG);
		}
	
	}
	
	
	
}
