package com.cinlife.cingrous.cinlife;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> user1 = new ArrayList<String>();
        user1.add("In Time : 12:00 PM");
        user1.add("Out Time : 5:60 PM");
        user1.add("Activity : Testing");


        List<String> user2 = new ArrayList<String>();
        user2.add("In Time : 12:00 PM");
        user2.add("Out Time : 5:60 PM");
        user2.add("Activity : Testing");

        List<String> user3 = new ArrayList<String>();
        user3.add("In Time : 12:00 PM");
        user3.add("Out Time : 5:60 PM");
        user3.add("Activity : Testing");


        expandableListDetail.put("Arun", user1);
        expandableListDetail.put("Sanjay", user2);
        expandableListDetail.put("Prathvik", user3);
        return expandableListDetail;
    }
}
