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
public class NotificationsServiceReqSpecification {
    @Builder.Default
    String eventId = EVENT_ID;
    @Builder.Default
    String eventType = ROOT_EVENT_TYPE;
    @Builder.Default
    String dataVersion = DATA_VERSION;
    @Builder.Default
    String metadataVersion = METADATA_VERSION;
    @Builder.Default
    String sourceSystem = SOURCE_SYSTEM;
    @Builder.Default
    String domain = DOMAIN;
    @Builder.Default
    String dataEventType =DATA_EVENT_TYPE;
    @Builder.Default
    String sourceAction = SOURCE_ACTION;
    @Builder.Default
    String bulkSend = BULK_SEND;
    @Builder.Default
    String sourceId =SOURCE_ID;
    @Builder.Default
    String attendee_name = ATTENDEE_NAME;
    @Builder.Default
    String event_name = EVENT_NAME;
    @Builder.Default
    String date_time = DATE_TIME;
    @Builder.Default
    String innerEventType = INNER_DATA_EVENT_TYPE;
    @Builder.Default
    String location = LOCATION;
    @Builder.Default
    String virtual_link = VIRTUAL_LINK;
    @Builder.Default
    String seats_booked = SEATS_BOOKED;
    @Builder.Default
    String startTime = START_TIME;
    @Builder.Default
    String endTime = END_TIME;
    @Builder.Default
    String id = ID;
    @Builder.Default
    String email = EMAIL;




}
