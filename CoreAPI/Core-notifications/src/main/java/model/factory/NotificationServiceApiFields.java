package model.factory;

public class NotificationServiceApiFields {


 // Event-related constants
 public static final String EVENT_ID = "e603ff5f-fff2-4897-99d7-828af78a6225"; //need to confirm
 public static final String ROOT_EVENT_TYPE = "EVENT SCHEDUL";

 //public static final String DATA = "data";
 public static final String SOURCE_SYSTEM = "EVENTS";
 public static final String DOMAIN = "EVENT_BOOKING";
 public static final String DATA_EVENT_TYPE = "EVENT_BOOKING";
 public static final String SOURCE_ACTION = "CREATE";
 public static final String BULK_SEND = "false";
 public static final String SOURCE_ID = "846fb244-72a3-423b-b5ab-52f3f54885ea"; //need to confirm

 // Inner Data section constants
 //public static final String INNERDATA = "inner_data";
 public static final String ATTENDEE_NAME = "VBC testing";
 public static final String EVENT_NAME = "VBC tested";
 public static final String DATE_TIME = "VBC test";
 public static final String INNER_DATA_EVENT_TYPE = "Online"; // Renamed to avoid conflict with outer "eventType"
 public static final String LOCATION = "VBC test";
 public static final String VIRTUAL_LINK = "https://qaevents.venturebacked.co/";
 public static final String SEATS_BOOKED = "1";
 public static final String START_TIME = "20241213T190000Z";
 public static final String END_TIME = "20241213T200000Z";

 // Receipients section constants
 //public static final String RECEIPIENTS = "receipients";  // need to check
 public static final String ID = "846fb244-72a3-423b-b5ab-52f3f54885ea";  // need to check
 public static final String EMAIL = "sharathmuraleedharan@gmail.com";  // need to confirm mail id

 // Meta-data constants
 public static final String DATA_VERSION = "1";
 public static final String METADATA_VERSION = "1";

 // User Notification Constants
 public static final Boolean IS_DELETED = false;
 public static final String STATUS = "SENT";


}
