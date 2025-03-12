package model;

import lombok.Getter;
import model.factory.NotificationServiceRequestType;

// Root class representing the entire JSON structure
public class UserNotificationController extends NotificationServiceRequestType {

    @Getter
    private Boolean isDeleted;
    @Getter
    private String status;

    public UserNotificationController setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public UserNotificationController setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public UserNotificationController build(UserNotificationReqSpecification userNotificationReqSpecification) {
        return new UserNotificationController().setIsDeleted(userNotificationReqSpecification.isDeleted)
                .setStatus(userNotificationReqSpecification.status);
    }

    @Override
    public UserNotificationController build(NotificationsServiceReqSpecification userNotificationReqSpecification) {
        return null;
    }
}
