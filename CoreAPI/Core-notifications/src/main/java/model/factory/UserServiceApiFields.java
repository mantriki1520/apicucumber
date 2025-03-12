package model.factory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserServiceApiFields {


    // UserProfile constants
    public static final String PRONOUN = "He";
    public static final List<String> USER_PROFILE_TYPES = Collections.unmodifiableList(Arrays.asList("INVESTOR"));
    public static final String ETHNIC_ORIGIN_ASIAN = "Asian";
    public static final String ACCREDITATION_DESCRIPTION = "Net worth over $1 million, excluding primary residence (individually or with spouse or partner)";
    public static final String GENDER = "MALE";
    public static final String DOB = "12/19/2000";
    public static final String DPLINK ="";
    public static final String COMMUNICATIONCHARTUSERID = "";
    public static final List<Integer> INDUSTRY = Collections.unmodifiableList(Arrays.asList(0));
//    public static final List<UserProfileController.SocialLink> SOCIAL_LINKS = Collections.unmodifiableList(Arrays.asList(
//            new UserProfileController.SocialLink().setName()
//    ));
    public static final String SOCIALLINKS_NAME = "Instagram";
    public static final String SOCIALLINKS_LINK = "Instagram";


    // Address constants
    public static final String ADDRESS_LINE1 = "Bangalore";
    public static final String ADDRESS_LINE2 = "Bangalore";
    public static final String CITY = "Bangalore";
    public static final String STATE = "Karnataka";
    public static final String COUNTRY = "India";
    public static final String ZIP = "560068";
    public static final String COUNTRY_CODE = "IND";

    // Profile SubTypes
    public static final Map<String, List<String>> PROFILE_SUBTYPES = Map.of(
            "INVESTOR", Arrays.asList("Angel Investor")
    );

    // Accreditation status
    public static final boolean IS_ACREDITED = true;


}
