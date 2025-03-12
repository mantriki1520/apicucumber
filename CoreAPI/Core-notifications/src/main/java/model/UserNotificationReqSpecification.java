package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static model.factory.NotificationServiceApiFields.*;


@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserNotificationReqSpecification {
    @Builder.Default
    Boolean isDeleted = IS_DELETED;
    @Builder.Default
    String status = STATUS;

}
