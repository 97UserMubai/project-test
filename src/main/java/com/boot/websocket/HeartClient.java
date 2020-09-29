package com.boot.websocket;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/9/15 10:37
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
import java.net.Socket;
import java.util.*;

public class HeartClient {

    /**
     * 服务端地址
     */
    private static final String HOST = "127.0.0.1";

    /**
     * 服务端端口
     */
    private static final int PORT = 12345;

    /**
     * 心跳间隔时间（毫秒）
     */
    private static final long HEARTBEAT_INTERVAL = 2 * 1000;

    /**
     * 心跳超时时间（毫秒）
     */
    private static final long HEARTBEAT_TIMEOUT = 10 * 1000;

    /**
     * 以下为与客户端约定的指令，分别是：心跳、心跳回执和退出回执，退出由客户端触发
     */
    private static final String HEARTBEAT = "heartbeat";
    private static final String HEARTBEAT_RECEIPT = "heartbeat_receipt";
    private static final String EXIT_RECEIPT = "exit_receipt";

    /**
     * 连接通道，因为重连需要新建连接，所以存一个Map，保证新连接可以被共用
     */
    private static Map<String, Socket> socketMap = new HashMap<>(16);

    /**
     * 连接状态，因为重连需要新建连接，所以存一个Map，缓存共用
     */
    private static Map<String, Boolean> connectionStatusMap = new HashMap<>(16);

    /**
     * 心跳回执，因为重连需要新建连接，所以存一个Map，缓存共用
     */
    private static Map<String, Date> heartbeatReceiptTimeMap = new HashMap<>(16);

    /**
     * 连接重试，因为重连需要新建连接，所以存一个Map，缓存共用，null：正常连接，true：需要重新连接，false：不用重新连接
     */
    private static Map<String, Boolean> connectionRetryStatusMap = new HashMap<>(16);

    /**
     * 重试次数，因为重连需要新建连接，所以存一个Map，缓存共用
     */
    private static Map<String, Integer> connectionRetryCountMap = new HashMap<>();

    /**
     * 重试间隔
     */
    private static final long CONNECTION_RETRY_INTERVAL = 5 * 1000;


    public static void main(String[] args) {
        // 生成唯一key，保证连接重试后缓存共用
        String threadKey = UUID.randomUUID().toString();
//        new Client().start(threadKey);
    }

    /**
     * 功能描述:
     * <客户端启动>
     *
     * @param  threadKey 1
     * @return void
     * @author zhoulipu
     * @date   2019/8/8 15:51
     */
    private void start(String threadKey) {
        try {
            // 客户端开启，建立连接
            Socket socket = new Socket(HOST, PORT);
            // 当连接建立成功，立即缓存（连接失败会抛异常，不会缓存）
            socketMap.put(threadKey, socket);
            // 添加首次连接时间作为心跳回执
            heartbeatReceiptTimeMap.put(threadKey, new Date());
            System.out.println("连接服务器成功，本机地址：" + socket.getLocalSocketAddress().toString().replace("/", ""));
            // 更新连接状态
            connectionStatusMap.put(threadKey, true);
            // 重置重试状态
            connectionRetryStatusMap.put(threadKey, null);
            // 重置重试次数
            connectionRetryCountMap.put(threadKey, 0);
            // 开启新线程处理消息
            new MessageListener(threadKey).start();
        } catch (IOException e) {
            e.getStackTrace();
        }
        // 开启新线程监听心跳
        new HeartbeatListener(threadKey).start();
        // 开启新线程连接重试
        new ConnectionRetryListener(threadKey).start();
    }

    /**
     * 消息处理
     */
    class MessageListener extends Thread {

        private String threadKey;

        private MessageListener(String threadKey) {
            this.threadKey = threadKey;
        }

        @Override
        public void run() {
            try {
                String msg;
                // 当连接正常时，循环处理消息
                while (connectionStatusMap.get(threadKey)) {
                    msg = receiveMsg(threadKey);
                    if (msg == null) {
                        // 表示连接已断开，等待心跳监听线程来处理
                        continue;
                    }
                    if (HEARTBEAT_RECEIPT.equals(msg)) {
                        // 收到心跳回执，记录时间
                        heartbeatReceiptTimeMap.put(threadKey, new Date());
                    } else if (EXIT_RECEIPT.equals(msg)) {
                        // 收到退出回执
                        // 设置重试状态，防止重新连接
                        connectionRetryStatusMap.put(threadKey, false);
                        System.out.println("[服务端]：我已收到关闭指令，连接已关闭");
                        // 关闭连接
                        try {
                            socketMap.get(threadKey).close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // 关闭程序 System.exit(0)可以关闭调用此线程的其他方法，相当于连根拔除
                        System.exit(0);
                    } else {
                        System.out.println("[服务端]：" + msg);
                        // 开启消息处理线程，防止控制台无输入时导致心跳回执时间无法更新
                        new TaskHandler (threadKey).start();
                    }
                }
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }

    /**
     * 消息处理
     */
    class TaskHandler extends Thread {

        private String threadKey;

        private TaskHandler (String threadKey) {
            this.threadKey = threadKey;
        }

        // 消息正常传输
        @Override
        public void run() {
            try {
                System.out.print("[客户端]：");
                // 这里有个问题，因为客户端从控制台获取信息，当一次连接失败时，如果没控制台信息输入，这个线程卡住。等重新连接后，再在控制台输入，会丢失一次消息。正常业务处理不会出现这种情况
                sendMsg(threadKey, new Scanner(System.in).nextLine());
            } catch (IOException e) {
                e.getStackTrace();
            }
        }

    }

    /**
     * 心跳监测
     */
    class HeartbeatListener extends Thread {

        private String threadKey;

        private HeartbeatListener(String threadKey) { this.threadKey = threadKey; }

        @Override
        public void run() {
            try {
                Date time, now;
                // 当心跳存在时，循环处理消息
                while ((time = heartbeatReceiptTimeMap.get(threadKey)) != null) {
                    now = new Date();
                    // 比对当前时间和最新心跳回执
                    if (now.getTime() - time.getTime() > HEARTBEAT_TIMEOUT) {
                        // 服务端心跳超时，更改连接状态和重试状态，关闭线程，等待重试
                        connectionStatusMap.put(threadKey, false);
                        connectionRetryStatusMap.put(threadKey, true);
                        // 关闭连接
                        try {
                            socketMap.get(threadKey).close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    // 发送心跳
                    sendMsg(threadKey, HEARTBEAT);
                    // 心跳间隔
                    try {
                        Thread.sleep(HEARTBEAT_INTERVAL);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }

    /**
     * 连接重试
     */
    class ConnectionRetryListener extends Thread {

        private String threadKey;

        private ConnectionRetryListener(String threadKey) { this.threadKey = threadKey; }

        @Override
        public void run() {
            // 连接正常或需要重连时循环监听
            while (connectionRetryStatusMap.get(threadKey) == null || connectionRetryStatusMap.get(threadKey)) {
                // 需要重连
                if (connectionRetryStatusMap.get(threadKey) != null) {
                    // 更新重连次数
                    connectionRetryCountMap.put(threadKey, connectionRetryCountMap.getOrDefault(threadKey, 0) + 1);
                    // 这个没什么用，我只是为了控制台打印好看
                    if (connectionRetryCountMap.get(threadKey) == 1) {
                        System.out.println();
                    }
                    System.out.println("连接已断开，正在尝试第" + connectionRetryCountMap.get(threadKey) + "次连接...");
                    // 重试间隔
                    try {
                        Thread.sleep(CONNECTION_RETRY_INTERVAL);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 连接重试
//                    new Client().start(threadKey);
                    return;
                }
            }
        }
    }

    /**
     * 功能描述:
     * <发送消息>
     *
     * @param threadKey 1
     * @param msg       2
     * @return void
     * @author zhoulipu
     * @date 2019/8/8 15:45
     */
    private void sendMsg(String threadKey, String msg) throws IOException {
        OutputStream out = socketMap.get(threadKey).getOutputStream();
        PrintWriter writer = new PrintWriter(out);
        // 使用pw.write(msg); msg末尾必须加"\n"转义， println自动添加转义
        writer.println(msg);
        writer.flush();
    }

    /**
     * 功能描述:
     * <接受消息>
     *
     * @param threadKey 1
     * @return java.lang.String
     * @author zhoulipu
     * @date 2019/8/8 15:45
     */
    private String receiveMsg(String threadKey) throws IOException {
        InputStream in = socketMap.get(threadKey).getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return reader.readLine();
    }

}

