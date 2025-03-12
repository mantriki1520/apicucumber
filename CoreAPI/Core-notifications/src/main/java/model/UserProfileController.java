package model;

import lombok.Getter;
import model.factory.UserServiceRequestType;

import java.util.List;
import java.util.Map;

// Root class representing the entire JSON structure
public class UserProfileController extends UserServiceRequestType {

    @Getter
    private String pronoun;
    @Getter
    private List<String> userProfileTypes;
    @Getter
    private Address address;
    @Getter
    private String ethnicOrigin;
    @Getter
    private Map<String, List<String>> profileSubTypes;
    @Getter
    private Boolean isAccredited;
    @Getter
    private String accreditationDes;
    @Getter
    private String dpLink;
    @Getter
    private List<SocialLink> socialLinks;
    @Getter
    private String gender;
    @Getter
    private String dob;
    @Getter
    private String communicationChatUserId;
    @Getter
    private List<Integer> industry;


    public UserProfileController setPronoun(String pronoun) {
        this.pronoun = pronoun;
        return this;
    }

    public UserProfileController setUserProfileTypes(List<String> userProfileTypes) {
        this.userProfileTypes = userProfileTypes;
        return this;
    }

    public UserProfileController setAddress(Address address) {
        this.address = address;
        return this;
    }


    public UserProfileController setEthnicOrigin(String ethnicOrigin) {
        this.ethnicOrigin = ethnicOrigin;
        return this;
    }

    public UserProfileController setProfileSubTypes(Map<String, List<String>> profileSubTypes) {
        this.profileSubTypes = profileSubTypes;
        return this;
    }


    public UserProfileController setAccredited(Boolean isAccredited) {
        this.isAccredited = isAccredited;
        return this;
    }

    public UserProfileController setAccreditationDes(String accreditationDes) {
        this.accreditationDes = accreditationDes;
        return this;
    }

    public UserProfileController setDpLink(String dpLink) {
        this.dpLink = dpLink;
        return this;
    }

    public UserProfileController setSocialLinks(List<SocialLink> socialLinks) {
        this.socialLinks = socialLinks;
        return this;
    }

    public UserProfileController setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public UserProfileController setDob(String dob) {
        this.dob = dob;
        return this;
    }

    public UserProfileController setCommunicationChatUserId(String communicationChatUserId) {
        this.communicationChatUserId = communicationChatUserId;
        return this;
    }

    public UserProfileController setIndustry(List<Integer> industry) {
        this.industry = industry;
        return this;
    }

    //nested class to map Address class to UserProfile Controller
    public static class Address {

        @Getter
        private String addressLine1;
        @Getter
        private String addressLine2;
        @Getter
        private String city;
        @Getter
        private String state;
        @Getter
        private String country;
        @Getter
        private String zip;
        @Getter
        private String countryCode;

        // Getters and Setters


        public Address setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
            return this;
        }


        public Address setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public Address setCity(String city) {
            this.city = city;
            return this;
        }

        public Address setState(String state) {
            this.state = state;
            return this;
        }

        public Address setCountry(String country) {
            this.country = country;
            return this;
        }

        public Address setZip(String zip) {
            this.zip = zip;
            return this;
        }

        public Address setCountryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }
    }

    public static class SocialLink {

        @Getter
        private String name;
        @Getter
        private String link;


        // Getters and Setters


        public SocialLink setName(String name) {
            this.name = name;
            return this;
        }


        public SocialLink setLink(String link) {
            this.link = link;
            return this;
        }
    }

    public UserProfileController build(CreateUserProfileReqSpecification createUserProfileReqSpecification) {

        return new UserProfileController().setPronoun(createUserProfileReqSpecification.pronoun)
                .setUserProfileTypes(createUserProfileReqSpecification.userProfileTypes)
                .setAddress(new Address().setAddressLine1(createUserProfileReqSpecification.addressLine1)
                        .setAddressLine2(createUserProfileReqSpecification.addressLine2)
                        .setCity(createUserProfileReqSpecification.city)
                        .setState(createUserProfileReqSpecification.state)
                        .setCountry(createUserProfileReqSpecification.country)
                        .setZip(createUserProfileReqSpecification.zip)
                        .setCountryCode(createUserProfileReqSpecification.countryCode))
                .setEthnicOrigin(createUserProfileReqSpecification.ethnicOrigin)
                .setProfileSubTypes(createUserProfileReqSpecification.profileSubTypes)
                .setAccredited(createUserProfileReqSpecification.isAccredited)
                .setAccreditationDes(createUserProfileReqSpecification.accreditationDes)
                .setDpLink(createUserProfileReqSpecification.dpLink)
                .setSocialLinks(List.of(new SocialLink().
                        setName(createUserProfileReqSpecification.socialLinks_name)
                        .setLink(createUserProfileReqSpecification.socialLinks_link)))
                .setGender(createUserProfileReqSpecification.gender)
                .setDob(createUserProfileReqSpecification.dob)
                .setCommunicationChatUserId(createUserProfileReqSpecification.communicationchartuserid)
                .setIndustry(createUserProfileReqSpecification.industry);

    }


}
