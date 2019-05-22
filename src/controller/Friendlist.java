package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.MakingFriends;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Friendlist extends AsynchroonRequestHandler{


    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Person man =  (Person) session.getAttribute("user");
        String quoteJSON = friendsToJSON(man.getVriendenlijst().getAllFriends());
        return quoteJSON;
    }
    public String friendsToJSON (List<Person> vrienden) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(vrienden);
    }

    public String toJSON (MakingFriends friend) throws JsonProcessingException {
        ArrayList<Person> vriendlijst = friend.getAllFriends();
        StringBuffer json = new StringBuffer();
        json.append("{\"friends\":");

        json.append("[");
        json.append("{ \"name\":\"");
        json.append( vriendlijst.get(0).getFirstName());
        json.append("\", \"status\":\"");
        json.append( vriendlijst.get(0).getStatus());
        json.append("\", \"userid\":\"");
        json.append( vriendlijst.get(0).getUserId());
        json.append("\" }");

        for( int i = 1; i < vriendlijst.size(); i++){
            json.append(",{ \"name\":\"");
            json.append( vriendlijst.get(i).getFirstName());
            json.append("\", \"status\":\"");
            json.append( vriendlijst.get(i).getStatus());
            json.append("\", \"userid\":\"");
            json.append( vriendlijst.get(i).getUserId());
            json.append("\" }");
        }
        json.append("]}");

        return json.toString();
    }
}
