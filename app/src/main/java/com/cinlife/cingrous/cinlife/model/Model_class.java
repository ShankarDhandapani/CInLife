package com.cinlife.cingrous.cinlife.model;

import android.net.Uri;

import java.util.Map;

public class Model_class {
    private String worker_type;
    private  String address;
    private String from_duration;
    private String to_duration;
    private String email;
    private String gender;
    private String name;
    private String college;
    private String project;
    private String phone;
    private String profilePicture;

    public Model_class(String address, String from_duration, String to_duration, String email,
                       String gender,String worker_type, String name, String college, String project, String phone,
                       String profilePicture) {
        this.address = address;
        this.from_duration = from_duration;
        this.to_duration = to_duration;
        this.email = email;
        this.gender = gender;
        this.worker_type = worker_type;
        this.name = name;
        this.college = college;
        this.project = project;
        this.phone = phone;
        this.profilePicture = profilePicture;
    }

    public Model_class(Map<String, Object> data) {
        this.address = (String) data.get("address");
        this.from_duration = (String) data.get("from_duration");
        this.to_duration = (String) data.get("to_duration");
        this.email = (String) data.get("email");
        this.gender = (String) data.get("gender");
        this.worker_type = (String) data.get("worker_type");
        this.name = (String) data.get("name");
        this.college = (String) data.get("college");
        this.project = (String) data.get("project");
        this.phone = (String) data.get("phone");
        this.profilePicture = (String) data.get("profilePicture");
    }

    public String getWorker_type() {
        return worker_type;
    }

    public void setWorker_type(String worker_type) {
        this.worker_type = worker_type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFrom_duration() {
        return from_duration;
    }

    public void setFrom_duration(String from_duration) {
        this.from_duration = from_duration;
    }

    public String getTo_duration() {
        return to_duration;
    }

    public void setTo_duration(String to_duration) {
        this.to_duration = to_duration;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
