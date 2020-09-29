package com.boot.schedual;

import com.boot.websocket.WebSocketServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/8/26 10:09
 * <h>定时任务测试</h>
 */
@Configuration
@EnableScheduling
public class SchedualTest {
    private static final long delaySeconds = 5 * 1000;

    @Scheduled(fixedRate = delaySeconds)
    private void test() {
//        System.out.println("我是输出测试的当前时间为：" + LocalDateTime.now().toString());
        System.out.println("当前websocket的会话大小:" + WebSocketServer.electricSocketMap.size());
        WebSocketServer.electricSocketMap.forEach((s, session) ->
                session.getAsyncRemote().sendText("当前测试时间" + LocalDateTime.now().toString()));
    }
}
