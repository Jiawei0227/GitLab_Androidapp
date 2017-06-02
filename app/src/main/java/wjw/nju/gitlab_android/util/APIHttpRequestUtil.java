package wjw.nju.gitlab_android.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangjiawei on 2017/5/31.
 */

public class APIHttpRequestUtil {

    public static String postJSON(String address_Http, JSONObject strJson) {

        String returnLine = "";
        try {

            System.out.println("**************开始http通讯**************");
            System.out.println("**************调用的接口地址为**************" + address_Http);
            System.out.println("**************请求发送的数据为**************" + strJson);

            URL my_url = new URL(address_Http);
            HttpURLConnection connection = (HttpURLConnection) my_url.openConnection();
            connection.setDoOutput(true);

            connection.setDoInput(true);

            connection.setRequestMethod("POST");

            connection.setUseCaches(false);

            connection.setInstanceFollowRedirects(true);

            connection.setRequestProperty("Content-Type", "application/json");

            connection.connect();
            DataOutputStream out = new DataOutputStream(connection

                    .getOutputStream());

            byte[] content = strJson.toString().getBytes("utf-8");

            out.write(content, 0, content.length);
            out.flush();
            out.close(); // flush and close

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));

            //StringBuilder builder = new StringBuilder();

            String line = "";

            System.out.println("Contents of post request start");

            while ((line = reader.readLine()) != null) {
                // line = new String(line.getBytes(), "utf-8");
                returnLine += line;

                System.out.println(line);

            }

            System.out.println("Contents of post request ends");

            reader.close();
            connection.disconnect();
            System.out.println("========返回的结果的为========" + returnLine);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnLine;

    }

    public static String getJSON(String address_Http, JSONObject strJson , String HeaderToken) {

        String returnLine = "";
        try {

            System.out.println("**************开始http通讯**************");
            System.out.println("**************调用的接口地址为**************" + address_Http);
            System.out.println("**************请求发送的数据为**************" + strJson);
            System.out.println("**************请求头部的token为************" + HeaderToken);
            URL my_url = new URL(address_Http);
            HttpURLConnection connection = (HttpURLConnection) my_url.openConnection();

            connection.setRequestMethod("GET");

            connection.setDoInput(true);

            connection.setUseCaches(false);

            connection.setInstanceFollowRedirects(true);

            connection.setRequestProperty("Content-Type", "application/json");

            connection.addRequestProperty("Authorization","Basic "+HeaderToken);

            connection.connect();

            returnLine = new String(getBytesByInputStream(connection.getInputStream()), "UTF-8");

            System.out.println("========返回的结果的为========" + returnLine);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnLine;

    }

    private static byte[] getBytesByInputStream(InputStream is) {
        byte[] bytes = null;
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(baos);
        byte[] buffer = new byte[1024 * 8];
        int length = 0;
        try {
            while ((length = bis.read(buffer)) > 0) {
                bos.write(buffer, 0, length);
            }
            bos.flush();
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }


}
