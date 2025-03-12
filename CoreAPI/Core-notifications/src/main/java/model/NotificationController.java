package model;

import lombok.Getter;
import model.factory.NotificationServiceRequestType;

import java.util.List;

// Root class representing the entire JSON structure
public class NotificationController extends NotificationServiceRequestType {

    @Getter
    private String eventId;
    @Getter
    private String eventType;
    @Getter
    private Data data;
    @Getter
    private String dataVersion;
    @Getter
    private String metadataVersion;


    public NotificationController setEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }


    public NotificationController setEventType(String eventType) {
        this.eventType = eventType;
        return this;
    }


    public NotificationController setData(Data data) {
        this.data = data;
        return this;
    }


    public NotificationController setDataVersion(String dataVersion) {
        this.dataVersion = dataVersion;
        return this;
    }


    public NotificationController setMetadataVersion(String metadataVersion) {
        this.metadataVersion = metadataVersion;
        return this;
    }

    // Nested Data class
    public static class Data {

        @Getter
        private String sourceSystem;
        @Getter
        private String domain;
        @Getter
        private String eventType;
        @Getter
        private String sourceAction;
        @Getter
        private String bulkSend;
        @Getter
        private String sourceId;
        @Getter
        private EventData eventData;
        @Getter
        private List<Recipient> receipients;


        public Data setSourceSystem(String sourceSystem) {
            this.sourceSystem = sourceSystem;
            return this;
        }


        public Data setDomain(String domain) {
            this.domain = domain;
            return this;
        }


        public Data setEventType(String eventType) {
            this.eventType = eventType;
            return this;
        }


        public Data setSourceAction(String sourceAction) {
            this.sourceAction = sourceAction;
            return this;
        }


        public Data setBulkSend(String bulkSend) {
            this.bulkSend = bulkSend;
            return this;
        }


        public Data setSourceId(String sourceId) {
            this.sourceId = sourceId;
            return this;
        }


        public Data setEventData(EventData eventData) {
            this.eventData = eventData;
            return this;
        }


        public Data setReceipients(List<Recipient> receipients) {
            this.receipients = receipients;
            return this;
        }


        // Nested EventData class (to map 'data' field inside 'data' field)
        public static class EventData {

            @Getter
            private String attendeeName;
            @Getter
            private String eventName;
            @Getter
            private String dateTime;
            @Getter
            private String eventType;
            @Getter
            private String location;
            @Getter
            private String virtualLink;
            @Getter
            private String seatsBooked;
            @Getter
            private String startTime;
            @Getter
            private String endTime;


            public EventData setAttendeeName(String attendeeName) {
                this.attendeeName = attendeeName;
                return this;
            }


            public EventData setEventName(String eventName) {
                this.eventName = eventName;
                return this;
            }


            public EventData setDateTime(String dateTime) {
                this.dateTime = dateTime;
                return this;
            }


            public EventData setEventType(String eventType) {
                this.eventType = eventType;
                return this;
            }


            public EventData setLocation(String location) {
                this.location = location;
                return this;
            }

            public EventData setVirtualLink(String virtualLink) {
                this.virtualLink = virtualLink;
                return this;
            }

            public EventData setSeatsBooked(String seatsBooked) {
                this.seatsBooked = seatsBooked;
                return this;
            }

            public EventData setStartTime(String startTime) {
                this.startTime = startTime;
                return this;
            }

            public EventData setEndTime(String endTime) {
                this.endTime = endTime;
                return this;
            }
        }

        // Nested Recipient class to map the recipients
        public static class Recipient {

            @Getter
            private String id;
            @Getter
            private String email;


            public Recipient setId(String id) {
                this.id = id;
                return this;
            }

            public Recipient setEmail(String email) {
                this.email = email;
                return this;
            }
        }
    }

    public NotificationController build(NotificationsServiceReqSpecification notificationsServiceReqSpecification) {

        return new NotificationController().setEventId(notificationsServiceReqSpecification.eventId)
                .setEventType(notificationsServiceReqSpecification.eventType)
                .setData(new Data().setSourceSystem(notificationsServiceReqSpecification.sourceSystem)
                        .setDomain(notificationsServiceReqSpecification.domain).setEventType(notificationsServiceReqSpecification.eventType)
                        .setSourceAction(notificationsServiceReqSpecification.sourceAction).setBulkSend(notificationsServiceReqSpecification.bulkSend).setSourceId(notificationsServiceReqSpecification.sourceId)
                        .setEventData(new Data.EventData().setAttendeeName(notificationsServiceReqSpecification.attendee_name)
                                .setEventName(notificationsServiceReqSpecification.event_name)
                                .setDateTime(notificationsServiceReqSpecification.date_time)
                                .setEventType(notificationsServiceReqSpecification.innerEventType)
                                .setLocation(notificationsServiceReqSpecification.location)
                                .setVirtualLink(notificationsServiceReqSpecification.virtual_link)
                                .setSeatsBooked(notificationsServiceReqSpecification.seats_booked)
                                .setStartTime(notificationsServiceReqSpecification.startTime)
                                .setEndTime(notificationsServiceReqSpecification.endTime))
                        .setReceipients(List.of(new Data.Recipient()
                                .setId(notificationsServiceReqSpecification.id), new Data.Recipient()
                                .setEmail(notificationsServiceReqSpecification.email))))
                .setDataVersion(notificationsServiceReqSpecification.dataVersion)
                .setMetadataVersion(notificationsServiceReqSpecification.metadataVersion);

    }
    @Override
    public UserNotificationController build(UserNotificationReqSpecification userNotificationReqSpecification) {
        return null;
    }
}
