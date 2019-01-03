package com.cinlife.cingrous.cinlife.model;

public class Employee_Entry {
    private String in_time;
    private String out_time;
    private String activity;
    private String name;

    public Employee_Entry() {
    }

    public Employee_Entry(String in_time, String out_time, String activity, String name) {
        this.in_time = in_time;
        this.out_time = out_time;
        this.activity = activity;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIn_time() {
        return in_time;
    }

    public void setIn_time(String in_time) {
        this.in_time = in_time;
    }

    public String getOut_time() {
        return out_time;
    }

    public void setOut_time(String out_time) {
        this.out_time = out_time;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
