package com.bat.io.bio.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * BIO
 *
 * @author ZhengYu
 * @version 1.0 2020/9/5 11:19
 **/
public class BIONetworkClient {

    public static void main(String[] args) throws IOException {
        new BIONetworkClient().connect("127.0.0.1", 10054);
    }

    private void connect(String host, int port) throws IOException {
        // 1. 建立连接
        Socket socket = new Socket(host, port);

        // 2. 发送数据
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("Hello BIO!");

        // 3. 读取响应
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = in.readLine();
        System.out.println("client recv: " + line);

        // 4. 关闭连接
        socket.close();
    }
}
