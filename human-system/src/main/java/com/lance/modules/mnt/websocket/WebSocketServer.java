package com.lance.modules.mnt.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Component
@ServerEndpoint("/webSocket/{sid}")
public class WebSocketServer {

    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    private Session session;

    private String sid;

    @OnOpen
    public void onOPen(Session session, @PathParam(("sid")) String sid) {
        log.info("-------sid: " + sid);
        this.session = session;
        webSocketSet.removeIf(webSocketServer -> sid.equals(webSocketServer.sid));
        webSocketSet.add(this);
        this.sid = sid;
    }

}
