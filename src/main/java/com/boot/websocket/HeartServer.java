package com.boot.websocket;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/9/15 10:35
 * <h></h>
 * <p>
 *
 * </p>
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class HeartServer {

    /**
     * 客户端集合
     */
    private static List<Socket> clientList = new ArrayList<>();

    /**
     * 客户端心跳时间集合
     */
    private static Map<Socket, Date> heartbeatMap = new HashMap<>(16);

    /**
     * 心跳超时时间
     */
    private static final long TIMEOUT = 10 * 1000;

    /**
     * 服务端端口
     */
    private static final int PORT = 12345;

    /**
     * 以下为与客户端约定的指令，分别是：心跳、心跳回执、退出和退出回执
     */
    private static final String HEARTBEAT = "heartbeat";
    private static final String HEARTBEAT_RECEIPT = "heartbeat_receipt";
    private static final String EXIT = "exit";
    private static final String EXIT_RECEIPT = "exit_receipt";

    public static void main(String[] args) {
        new HeartServer().start();
    }

    /**
     * 功能描述:
     * <服务端启动>
     *
     *
     * @return void
     * @author zhoulipu
     * @date   2019/8/8 15:52
     */
    private void start() {
        try {
            // 服务端开启
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("服务端开启，等待客户端连接中...");
            // 循环监听客户端连接
            while (true) {
                // 等待客户端进行连接
                Socket client = server.accept();
                // 将客户端添加到集合
                clientList.add(client);
                System.out.println("有建立连接了，客户端地址：" + client.getRemoteSocketAddress().toString().replace("/", "") + "，当前连接数量：" + clientList.size());
                // 添加首次连接时间作为心跳
                heartbeatMap.put(client, new Date());
                // 开启新线程处理消息
                new MessageListener(client).start();
                // 开启新线程监测心跳
                new HeartbeatListener(client).start();
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    /**
     * 消息处理
     */
    class MessageListener extends Thread {

        private Socket client;

        private MessageListener(Socket socket) {
            this.client = socket;
        }

        @Override
        public void run() {
            try {
                // 客户端连接后立即下发消息
                sendMsg(client, "连接已经建立，请开始消息传输");
                String msg;
                // 当心跳存在时，循环处理消息
                while (heartbeatMap.get(client) != null) {
                    // 读取客户端消息
                    msg = receiveMsg(client);
                    if (msg == null) {
                        // 表示连接已断开，等待心跳监听线程来处理
                        continue;
                    }
                    if (HEARTBEAT.equals(msg)) {
                        // 记录客户端的心跳时间
                        heartbeatMap.put(client, new Date());
                        // 发送回执消息
                        sendMsg(client, HEARTBEAT_RECEIPT);
                    } else if (EXIT.equals(msg)) {
                        // 客户端主动下线，删除连接和心跳
                        clientList.remove(client);
                        heartbeatMap.remove(client);
                        // 发送回执消息
                        sendMsg(client, EXIT_RECEIPT);
                        // 关闭连接
                        try {
                            client.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("有断开连接了，客户端地址：" + client.getRemoteSocketAddress().toString().replace("/", "") + "，当前连接数量：" + clientList.size());
                    } else {
                        System.out.println("[" + client.getPort() + "]:" + msg);
                        sendMsg(client, "我已接收信息\"" + msg + "\"");
                    }
                }
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }

    /**
     * 心跳监测
     */
    class HeartbeatListener extends Thread {

        private Socket client;

        private HeartbeatListener(Socket socket) {
            this.client = socket;
        }

        @Override
        public void run() {
            Date time, now;
            // 当心跳存在时，循环处理消息
            while ((time = heartbeatMap.get(client)) != null) {
                now = new Date();
                // 比对当前时间和最新心跳时间
                if (now.getTime() - time.getTime() > TIMEOUT) {
                    // 客户端心跳超时（这里当作断开连接处理），删除连接和心跳
                    heartbeatMap.remove(client);
                    clientList.remove(client);
                    // 关闭连接
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("有心跳超时了，客户端地址：" + client.getRemoteSocketAddress().toString().replace("/", "") + "，当前连接数量：" + clientList.size());
                }
            }
        }
    }

    /**
     * 功能描述:
     * <发送消息>
     *
     * @param socket 1
     * @param msg    2
     * @return void
     * @author zhoulipu
     * @date 2019/8/8 15:03
     */
    private void sendMsg(Socket socket, String msg) throws IOException {
        OutputStream out = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(out);
        // 使用pw.write(msg); msg末尾必须加"\n"转义， println自动添加转义
        writer.println(msg);
        writer.flush();
    }

    /**
     * 功能描述:
     * <接受消息>
     *
     * @param socket 1
     * @return java.lang.String
     * @author zhoulipu
     * @date 2019/8/8 15:45
     */
    private String receiveMsg(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return reader.readLine();
    }

}

