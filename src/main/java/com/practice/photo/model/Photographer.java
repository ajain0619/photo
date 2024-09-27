package com.practice.photo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)  // Ignore any unknown fields in the JSON
public class Photographer {

    @Id
    private Long id;

    private String uid;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String avatar;
    private String gender;
    private String phoneNumber;
    private String dateOfBirth;

    // Store encrypted password
    @JsonIgnore
    private String encryptedPassword;

    // Password field received as plain text in JSON, will be encrypted and stored in `encryptedPassword`
    @Transient
    private String password;

    // Encrypted sensitive information
    @JsonIgnore
    private String encryptedSSN;

    @JsonIgnore
    private String encryptedCreditCard;

    @ElementCollection
    private List<String> eventType;

    // Address fields
    private String city;
    private String streetName;
    private String streetAddress;
    private String zipCode;
    private String state;
    private String country;

    // Subscription fields
    private String plan;
    private String status;
    private String paymentMethod;
    private String term;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<String> getEventType() {
        return eventType;
    }

    public void setEventType(List<String> eventType) {
        this.eventType = eventType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    // Encrypt password and store it in encryptedPassword
    public void setPassword(String password, String secretKey) throws Exception {
        this.encryptedPassword = encrypt(password, secretKey);
    }

    // Get encrypted password
    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    // Encrypt SSN and store it
    public void setSSN(String ssn, String secretKey) throws Exception {
        this.encryptedSSN = encrypt(ssn, secretKey);
    }

    // Get encrypted SSN
    public String getEncryptedSSN() {
        return encryptedSSN;
    }

    // Encrypt credit card and store it
    public void setCreditCard(String creditCard, String secretKey) throws Exception {
        this.encryptedCreditCard = encrypt(creditCard, secretKey);
    }

    // Get encrypted credit card
    public String getEncryptedCreditCard() {
        return encryptedCreditCard;
    }

    // Helper method to encrypt sensitive data
    private String encrypt(String data, String secretKey) throws Exception {
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    // Helper method to decrypt sensitive data (if needed)
    private String decrypt(String encryptedData, String secretKey) throws Exception {
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        return new String(cipher.doFinal(decodedData));
    }
}