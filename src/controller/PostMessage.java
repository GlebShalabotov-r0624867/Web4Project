package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Bericht;
import domain.Person;
import domain.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PostMessage extends AsynchroonRequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String chat = request.getParameter("bericht");
        String ontvanger = request.getParameter("ontvanger");
        HttpSession session = request.getSession();
        Person man =  (Person) session.getAttribute("user");
        String zender = man.getUserId();
        Bericht bericht = new Bericht(zender, ontvanger, chat);

        Service service = getPersonService();
        service.addNewMessage(bericht);
        System.out.println(messageToJSON(bericht));
        return "null";
    }

    public String messageToJSON (Bericht bericht) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(bericht);
    }
}
