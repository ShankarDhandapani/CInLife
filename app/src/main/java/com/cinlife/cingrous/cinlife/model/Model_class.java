package com.cinlife.cingrous.cinlife.model;

public class Model_class {
    private  String address, from_duration, to_duration
            ,email, gender, name, college, project, phone;


    public Model_class() {
    }

    public Model_class(String address, String from_duration, String to_duration, String email, String gender, String name, String college, String project, String phone) {
        this.address = address;
        this.from_duration = from_duration;
        this.to_duration = to_duration;
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.college = college;
        this.project = project;
        this.phone = phone;
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
}
