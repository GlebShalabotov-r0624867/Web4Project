import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/connect")

public class ChatServer {

    /*lijst van sessions bijhouden voor te zien welke gebruikers er zijn. */

    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session  ){
        sessions.add(session);
        System.out.println("connected" + session.getId());
    }


    @OnMessage
    public void onMessage (String message , Session session){
        //je kan van de chatservice een singleton maken waardoor je elk bericht kunt opslaan in uw blog.
        System.out.println("got message from " + session.getId());
        sendMassageToAll(message);

    }

    private void sendMassageToAll(String message){
        for(Session s: sessions){
            try{
                s.getBasicRemote().sendText(message);
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    @OnClose
    public void onClose(Session session){
        sessions.remove(session);
    }
}
