package edu.eci.arep;

import static spark.Spark.*;
import static spark.Spark.get;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MathService {

    private static final Logger LOGGER=LoggerFactory.getLogger(MathService.class);

    public static void main(String... args){
        port(getPort());
        staticFiles.location("/public");
        get("/linearsearch", (req,res) -> {
            String querylistStr = req.queryParams("list");
            String queryvalue = req.queryParams("value");
            String[] querylist = querylistStr.split(",");
            int output = linearSearch(querylist, queryvalue);
            return String.format("{\n" +
                    " \"operation\": \"linearSearch\",\n" +
                    " \"inputlist\": \"%s\",\n" +
                    " \"value\": \"%s\"\n" +
                    " \"output\":  \"%d\"\n" +
                    "}", querylistStr, queryvalue, output);
        });
        get("/binarysearch", (req,res) -> {
            String querylistStr = req.queryParams("list");
            String queryvalue = req.queryParams("value");
            String[] querylist = querylistStr.split(",");
            int output = binarySearch(querylist, queryvalue);
            return String.format("{\n" +
                    " \"operation\": \"binarySearch\",\n" +
                    " \"inputlist\": \"%s\",\n" +
                    " \"value\": \"%s\"\n" +
                    " \"output\":  \"%d\"\n" +
                    "}", querylistStr, queryvalue, output);
        });
    }
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }

    public static int linearSearch(String[] list, String value){
        for(int i=0; i<list.length; i++){
            if(list[i].equals(value)){
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(String[] list, String value){
        return binarySearchRecursive(list, value, 0, list.length-1);
    }

    public static int binarySearchRecursive(String[] list, String value, int low, int high){
        if (low > high) {
            return -1;
        }
        int mid = (low + high)/2;
        if (list[mid].equals(value)){
            return mid;
        }
        else if (list[mid].compareTo(value) > 0){
            return binarySearchRecursive(list, value, low, mid - 1);
        }
        else {
            return binarySearchRecursive(list, value, mid + 1, high);
        }
    }
}
