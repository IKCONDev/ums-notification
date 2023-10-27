package com.ikcon.tech.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ikcon.tech.entity.Notification;
import java.util.List;


@Repository
public interface NotificationRepo  extends JpaRepository<Notification, Long>{
	
    //@Query("from Notification where emailId=:emailId")
    //List<Notification> getAllNotifications(String emailId);
    
  
   @Query(value ="select * from notify_tab where notification_to=:emailId order by notify_id desc limit 10 ",nativeQuery = true)
   List<Notification> getAllNotifications(String emailId);

}
