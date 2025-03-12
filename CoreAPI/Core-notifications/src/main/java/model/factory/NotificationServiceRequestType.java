package model.factory;



import model.NotificationController;
import model.NotificationsServiceReqSpecification;
import model.UserNotificationController;
import model.UserNotificationReqSpecification;

public abstract class NotificationServiceRequestType {

    public static NotificationServiceRequestType selectController(String noticationServiceControllerType) {

        switch (noticationServiceControllerType.toUpperCase()) {

            case "POST EVENT NOTIFICATION": {

                return new NotificationController();

            }

            case "UPDATE USER NOTIFICATION": {

                return new UserNotificationController();

            }

            default: {

            }

        }

        return null;
    }

    public abstract NotificationServiceRequestType build(NotificationsServiceReqSpecification b);

    public abstract NotificationServiceRequestType build(UserNotificationReqSpecification b);


}
