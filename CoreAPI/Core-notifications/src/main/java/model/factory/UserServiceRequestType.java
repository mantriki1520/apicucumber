package model.factory;



import model.CreateUserProfileReqSpecification;
import model.UserProfileController;

public abstract class UserServiceRequestType {

    public static UserServiceRequestType selectController(String userServiceControllerType) {

        switch (userServiceControllerType.toUpperCase()) {

            case "POST USER PROFILE": {

                return new UserProfileController();

            }
            default: {

            }

        }

        return null;
    }

    public abstract UserProfileController build(CreateUserProfileReqSpecification b);

}
