package edu.eci.arep;

import static spark.Spark.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProxyServer {
    private static int counter = 0;
    private static String[] backends = {System.getenv("BACKEND1"), System.getenv("BACKEND2")};

    public static void main(String... args) {
        port(getPort());
        get("/linearsearch", (req, res) -> proxyRequest(req.queryString(), "/linearsearch"));
        get("/binarysearch", (req, res) -> proxyRequest(req.queryString(), "/binarysearch"));
    }

    private static String proxyRequest(String query, String path) throws Exception {
        String backend = backends[counter % 2];
        counter++;
        URL url = new URL(backend + path + "?" + query);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            return "{\"error\": \"Backend failed\"}";
        }
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
