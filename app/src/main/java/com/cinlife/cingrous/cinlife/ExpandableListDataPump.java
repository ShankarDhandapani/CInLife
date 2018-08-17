package com.cinlife.cingrous.cinlife;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> user_a = new ArrayList<String>();
        user_a.add("In Time : 5:00 PM");
        user_a.add("Out Time : 7:00 PM");
        user_a.add("Activity : Testing");

        List<String> user_b = new ArrayList<String>();
        user_b.add("In Time : 5:00 PM");
        user_b.add("Out Time : 7:00 PM");
        user_b.add("Activity : Testing");

        List<String> user_c = new ArrayList<String>();
        user_c.add("In Time : 5:00 PM");
        user_c.add("Out Time : 7:00 PM");
        user_c.add("Activity : Testing");

        expandableListDetail.put("User A", user_a);
        expandableListDetail.put("User B", user_b);
        expandableListDetail.put("User C", user_c);
        return expandableListDetail;
    }


}
