package com.example.herman.mobilekitchen.adapters;

import android.util.Log;

import com.example.herman.mobilekitchen.R;

import java.util.HashMap;
import java.util.Map;

public class ConnectorDrawable {
    private Map<String, Integer> map;

    public ConnectorDrawable(){
        map = new HashMap<String, Integer>();

        map.put("apple", R.drawable.apple);
        map.put("eggs", R.drawable.eggs);
        map.put("salami", R.drawable.salami);
    }

    public int getDrawableId(String name){

        String lower = name.toLowerCase();
        if (map.containsKey(lower))
            return map.get(lower);
        else
            return R.drawable.ic_launcher_background;
    };
}
