package ua.com.codefire.cms.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by human on 12/20/16.
 */

@ServerEndpoint("/ws")
public class IndexEndpoint {

//    private static final Map<String, String> messages = new LinkedHashMap<>();
    private static final List<Session> clients = new LinkedList<>();
    private static final List<String> messages = new LinkedList<>();

    @OnOpen
    public void onOpen(Session session) {
        clients.add(session);
        System.out.println("Open: " + session.getId());
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("Message: " + message);

        clients.stream()
//                .filter((s) -> s != session)
                .forEach((client) -> {
            try {
                client.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

//        for (Session client : clients) {
//            try {
//                client.getBasicRemote().sendText(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }

    @OnError
    public void onError(Session session, Throwable exception) {
        exception.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) {
        clients.remove(session);
        System.out.println("Close: " + session.getId());
    }
}
