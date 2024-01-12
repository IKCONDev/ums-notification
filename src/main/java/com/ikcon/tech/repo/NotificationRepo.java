package com.ikcon.tech.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ikcon.tech.entity.Notification;
import java.util.List;


@Repository
public interface NotificationRepo  extends JpaRepository<Notification, Long>{
	
    //@Query("from Notification where emailId=:emailId")
    //List<Notification> getAllNotifications(String emailId);
    
  
   @Query(value ="select * from notify_tab where notification_to=:emailId and status='Unread' order by notify_id desc ",nativeQuery = true)
   List<Notification> getAllNotifications(String emailId);
   
   @Query(value ="SELECT COUNT(*) FROM notify_tab WHERE notification_to = :emailId AND status = 'Unread'", nativeQuery = true)
   long countUnreadNotificationsByEmailId(String emailId);

   
}
