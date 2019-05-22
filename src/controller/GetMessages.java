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
import java.util.ArrayList;
import java.util.List;

public class GetMessages extends AsynchroonRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        System.out.println("got user");
        Person man =  (Person) session.getAttribute("user");
        String zender = man.getUserId();
        String ontvanger = (String) request.getParameter("ontvanger");
        Service service = getPersonService();

        ArrayList<Bericht> berichten = service.getMassagesOf(zender,ontvanger);
        String berichtenJSON =  messagesToJSON(berichten);
        return berichtenJSON;
    }


        public String messagesToJSON (ArrayList<Bericht> berichten) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(berichten);
        }
}
