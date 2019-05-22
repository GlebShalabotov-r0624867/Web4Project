package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Status extends AsynchroonRequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Person man = (Person) session.getAttribute("user");
        if (man.getStatus().equals("offline")){
             man.setStatus("online");
        }
        String status = man.getStatus();

        System.out.println(statusToJson(status));
        return statusToJson(status);
    }

    public String statusToJson(String status){
        String json = "{ \"status\" : \"" + status + "\"}" ;
        return json;
    }
}
