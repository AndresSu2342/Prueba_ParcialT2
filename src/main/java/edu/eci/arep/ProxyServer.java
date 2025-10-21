package edu.eci.arep;

import static spark.Spark.*;

public class ProxyServer {
    public static void main(String... args){
        port(getPort());
        staticFiles.location("/public");
        get("/linearsearch", (req,res) -> {
            String querylistStr = req.queryParams("list");
            String queryvalue = req.queryParams("value");
            String[] querylist = querylistStr.split(",");
            int output = MathService.linearSearch(querylist, queryvalue);
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
            int output = MathService.binarySearch(querylist, queryvalue);
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

}
