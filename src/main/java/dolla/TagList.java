package dolla;

import dolla.task.Record;

import java.util.HashMap;

public class TagList {

    public static HashMap<String, Record> tagList = new HashMap<>();; //change it to store the index of the record

    public void addTag(String tag, Record record) {
        try {
            tagList.put(tag, record);
        } catch (Exception e) { //todo: change
            System.out.println("error handling tag");
        }
    }
}
