package model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
import java.util.Map;

import static model.factory.UserServiceApiFields.*;


@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserProfileReqSpecification {
    @Builder.Default
    String pronoun = PRONOUN;
    @Builder.Default
    List<String> userProfileTypes = USER_PROFILE_TYPES;
    @Builder.Default
    String addressLine1 = ADDRESS_LINE1;
    @Builder.Default
    String addressLine2 = ADDRESS_LINE2;
    @Builder.Default
    String city = CITY;
    @Builder.Default
    String state = STATE;
    @Builder.Default
    String country =COUNTRY;
    @Builder.Default
    String zip = ZIP;
    @Builder.Default
    String countryCode = COUNTRY_CODE;
    @Builder.Default
    String ethnicOrigin =ETHNIC_ORIGIN_ASIAN;
    @Builder.Default
    Map<String, List<String>> profileSubTypes = PROFILE_SUBTYPES;
    @Builder.Default
    Boolean isAccredited = IS_ACREDITED;
    @Builder.Default
    String accreditationDes = ACCREDITATION_DESCRIPTION;
    @Builder.Default
    String dpLink = DPLINK;
    @Builder.Default
    String socialLinks_name = SOCIALLINKS_NAME;
    @Builder.Default
    String socialLinks_link = SOCIALLINKS_LINK;
    @Builder.Default
    String gender = GENDER;
    @Builder.Default
    String dob = DOB;
    @Builder.Default
    String communicationchartuserid = COMMUNICATIONCHARTUSERID;
    @Builder.Default
    List<Integer> industry = INDUSTRY;




}
