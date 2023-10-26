package com.ikcon.tech.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ikcon.tech.entity.Notification;
import com.ikcon.tech.service.NotificationService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/notification")
@CrossOrigin(origins="http://localhost:4200")
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
			log.info("Exception occured while executing the getAllNotifications()"+ e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> saveNotificationDetails(Notification notification){
		try {
		   Notification savedNotification = notificationService.SaveNotification(notification);
		   return new ResponseEntity<>(savedNotification,HttpStatus.CREATED);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
}
