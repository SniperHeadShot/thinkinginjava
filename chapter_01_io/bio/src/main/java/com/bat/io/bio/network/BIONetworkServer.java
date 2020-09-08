package com.bat.io.bio.network;

import com.bat.thinkinginjava.common.util.TimeUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * BIO
 *
 * @author ZhengYu
 * @version 1.0 2020/9/5 11:19
 **/
public class BIONetworkServer {

    public static void main(String[] args) throws IOException {
        new BIONetworkServer().bind(10054);
    }

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            1,
            1,
            30,
            TimeUnit.MINUTES,
            new LinkedBlockingDeque<>(),
            new ThreadFactoryBuilder().setNameFormat("thread_bio_server_%d").build()
    );

    private void bind(int port) throws IOException {
        // 1. 监听端口
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("bio server bind: " + port);
        while (true) {
            // 2. 阻塞, 等待客户端连接
            Socket socket = serverSocket.accept();

            THREAD_POOL_EXECUTOR.execute(new BIOSocketHandler(socket));
        }
    }

    private static class BIOSocketHandler implements Runnable {

        private Socket socket;

        BIOSocketHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader in;
            PrintWriter out;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // 3. 阻塞，处理客户端数据
                String line = in.readLine();
                System.out.println("server recv: " + line);

                out = new PrintWriter(socket.getOutputStream(), true);
                out.println(TimeUtil.nowFormat());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
