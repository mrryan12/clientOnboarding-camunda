package com.realcoderz;

import static org.hamcrest.CoreMatchers.containsString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test {
	
	public static void main(String[] args) {
		
		
		
		Map map = new HashMap();
		List<String> list = new ArrayList<>();
		list.add("name");
		list.add("email");
		list.add("contact");
		list.add("rrr");
		
		map.put("name", "rehan");
		map.put("email", "rehan@gmail.com");
		map.put("contact", "");
		
		String key[] = {"name","email","contact"};
		
		System.out.println(Arrays.stream(key).allMatch(d -> map.containsKey(d))+"---testttttt");
		
	
	    Set keySet = map.keySet();
	   System.out.println(keySet.containsAll(list) + " NEw");
		List<String> list2 = new ArrayList<>();
		
       map.keySet().stream().forEach(d -> list.contains(d)) ;
    	  
      
		
		
		
		
		
		
		System.out.println(list.containsAll(map.keySet()) +" test");
		
		System.out.println(map.containsKey("name"));
        boolean b = map.values().stream().noneMatch(data -> data.toString().trim().isEmpty());
        System.out.println(b);
	}

}
