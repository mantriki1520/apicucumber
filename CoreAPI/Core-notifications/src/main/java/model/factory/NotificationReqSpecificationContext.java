package model.factory;

import model.NotificationsServiceReqSpecification;

public class NotificationReqSpecificationContext {

    private NotificationsServiceReqSpecification.NotificationsServiceReqSpecificationBuilder notificationsServiceReqSpecificationBuilder;

    public NotificationReqSpecificationContext(){
        this.notificationsServiceReqSpecificationBuilder=NotificationsServiceReqSpecification.builder();
    }
}
