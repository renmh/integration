package com.pansky.integration.common.test;

public class TestR {

	/**
	 * @param args
	 */
	public static void main(String[] strs){
        java.util.Properties props = System.getProperties();
        java.util.Enumeration keys = props.keys();
        String key = null;
        while(keys.hasMoreElements()){
            key = keys.nextElement().toString();
            System.out.println(key + "=" + props.get(key));
        }}
}
