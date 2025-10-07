package com.ecommerce.Notification.repository;

import com.ecommerce.Notification.model.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
