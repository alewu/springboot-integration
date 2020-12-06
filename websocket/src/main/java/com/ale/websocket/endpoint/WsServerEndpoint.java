package com.ale.websocket.endpoint;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * The type Ws server endpoint.
 *
 * @author alewu
 * @date 2020 /7/7
 */
@ServerEndpoint("/ws")
@Component
@Slf4j
@Data
public class WsServerEndpoint {

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的WsServerEndpoint对象。
     * 在外部可以获取此连接的所有websocket对象，并能对其触发消息发送功能，我们的定时发送核心功能的实现在与此变量
     */
    private static CopyOnWriteArraySet<WsServerEndpoint> webSocketSet = new CopyOnWriteArraySet<>();


    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    public static Set<WsServerEndpoint> getWebSocketSet() {
        return webSocketSet;
    }

    /**
     * 连接成功
     *
     * @param session the session
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        log.info("连接成功,{}", session.getId());
    }

    /**
     * 连接关闭
     *
     * @param session the session
     */
    @OnClose
    public void onClose(Session session) {
        log.info("连接关闭");
    }

    /**
     * 接收到消息
     *
     * @param text the text
     * @return the string
     * @throws IOException the io exception
     */
    @OnMessage
    public String onMsg(String text) throws IOException {
        return "servet 发送：" + text;
    }

    /**
     * 发送消息，在定时任务中会调用此方法
     *
     * @param message the message
     * @throws IOException the io exception
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);

    }
}
