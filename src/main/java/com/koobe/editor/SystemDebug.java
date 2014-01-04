package com.koobe.editor;

/**
 * Created by lyhcode on 2014/1/3.
 */
public class SystemDebug {
    public static void main(String[] args) {


        for (Object key : System.getProperties().keySet()) {

            System.out.println(key);
        }

    }
}
