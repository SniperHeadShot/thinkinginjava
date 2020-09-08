package com.bat.io.nio.network;

import com.bat.thinkinginjava.common.util.TimeUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * NIO 服务端
 *
 * @author ZhengYu
 * @version 1.0 2020/9/5 13:08
 **/
public class NioNetworkServer {

    public static void main(String[] args) throws IOException {
        new NioNetworkServer().bind(10054);
    }

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            1,
            1,
            30,
            TimeUnit.MINUTES,
            new LinkedBlockingDeque<>(),
            new ThreadFactoryBuilder().setNameFormat("thread_bio_server_%d").build()
    );

    public void bind(int port) throws IOException {
        // 1. 创建多路复用器
        Selector selector = Selector.open();

        // 2. 打开 ServerSocketChannel
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        // 3. 设置为非阻塞模式
        serverSocket.configureBlocking(false);
        // 4. 监听端口
        serverSocket.socket().bind(new InetSocketAddress(port), 1024);
        // 5. 注册 ServerSocketChannel 到多路复用器上用于处理客户端的连接请求
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);

        THREAD_POOL_EXECUTOR.execute(() -> {
            while (true) {
                try {
                    // 6. 多路复用器扫描事件并遍历处理
                    selector.select(1000);

                    Set<SelectionKey> selected = selector.selectedKeys();
                    selected.forEach(selectionKey -> {
                        try {
                            // 处理事件
                            dispatch(selector, selectionKey);
                        } catch (Exception e) {
                            selectionKey.cancel();
                            if (selectionKey.channel() != null) {
                                try {
                                    selectionKey.channel().close();
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            }
                            e.printStackTrace();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // 多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动去注册并关闭，所以不需要重复释放资源
            // selector.close();
        });
    }

    private void dispatch(Selector selector, SelectionKey selectionKey) throws IOException {
        // SelectionKey 是否有效
        if (!selectionKey.isValid()) {
            return;
        }

        // 连接事件
        if (selectionKey.isAcceptable()) {
            ServerSocketChannel socketChannel = (ServerSocketChannel) selectionKey.channel();

            // 获取和客户端连接的通道
            SocketChannel channel = socketChannel.accept();
            if (channel != null) {
                channel.configureBlocking(false);

                // 客户端通道注册到多路复用器上
                channel.register(selector, SelectionKey.OP_READ);
            }
        }

        // 写事件
        if (selectionKey.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            int readBytes = socketChannel.read(readBuffer);
            if (readBytes > 0) {
                readBuffer.flip();
                byte[] bytes = new byte[readBuffer.remaining()];
                readBuffer.get(bytes);
                System.out.println("Server receive: " + new String(bytes, Charset.forName("UTF-8")));
            } else if (readBytes < 0) {
                // 对端链路关闭
                selectionKey.cancel();
                socketChannel.close();
            } else {
                System.out.println("读到 0 字节, 忽略");
            }

            doWrite(socketChannel, TimeUtil.nowFormat());
        }

        // 读事件
        if (selectionKey.isWritable()) {
            System.out.println("写事件");
        }
    }

    private void doWrite(SocketChannel channel, String response) throws IOException {
        if (response != null && response.trim().length() > 0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }
}
