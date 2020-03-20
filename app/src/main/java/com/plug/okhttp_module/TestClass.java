package com.plug.okhttp_module;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.net.SocketFactory;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by A35 on 2020/3/20
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public class TestClass {
    private static String HOST = "117.158.216.2";
    private static int PORT = 8082;

    public static void main(String[] args) {
        socketHttpsGet();
    }

    /**
     * GET / HTTP/1.1
     * Host: www.baidu.com
     */
    public static void socketHttpsGet() {
        try {
            Socket socket = SSLSocketFactory.getDefault().createSocket("www.baidu.com", 443);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("GET / HTTP/1.1\r\n");
            bufferedWriter.write("Host: www.baidu.com\r\n\r\n");
            bufferedWriter.flush();
            while (true) {
                InputStream stream = socket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                String read = null;
                if ((read = bufferedReader.readLine()) != null) {
                    System.out.println(read);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * HttpGet
     * http://117.158.216.2:8082/zkhonry-lyt-interface/menu/queryCommonMenuTree.action
     * GET /zkhonry-lyt-interface/menu/queryCommonMenuTree.action HTTP/1.1
     * Host: 117.158.216.2:8082
     */
    public static void socketHttpGet() {
        try {
            Socket socket = new Socket(HOST, PORT);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // 请求头
            bufferedWriter.write("GET /zkhonry-lyt-interface/menu/queryCommonMenuTree.action HTTP/1.1\r\n");
            bufferedWriter.write("Host: " + HOST + ":" + PORT + "\r\n\r\n");
            bufferedWriter.flush();
            while (true) {
                InputStream stream = socket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                String read = null;
                if ((read = bufferedReader.readLine()) != null) {
                    System.out.println(read);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * POST /v3/weather/weatherInfo?city=110101&key=13cb58f5884f9749287abbead9c658f2 HTTP/1.1
     * Content-Length: 48
     * Host: restapi.amap.com
     * Content-Type: application/x-www-form-urlencoded
     * city=110101&key=13cb58f5884f9749287abbead9c658f2
     */
    private static void socketHTTPPost() {
        try {
            Socket socket = new Socket("restapi.amap.com", 80); //  http:80
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write("POST /v3/weather/weatherInfo?city=110101&key=13cb58f5884f9749287abbead9c658f2 HTTP/1.1\r\n");
            bw.write("Content-Length: 48\r\n");
            bw.write("Content-Type: application/x-www-form-urlencoded\r\n");
            bw.write("Host: restapi.amap.com\r\n\r\n");
            // 下面是 POST请求体
            bw.write("city=110101&key=13cb58f5884f9749287abbead9c658f2\r\n");
            bw.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String readLine = null;
                if ((readLine = br.readLine()) != null) {
                    System.out.println("响应的数据：" + readLine);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
