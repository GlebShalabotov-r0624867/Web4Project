package controller;

import domain.Person;
import domain.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostAngular extends AsynchroonRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String status = (String) request.getParameter("status");
        String id = (String) request.getParameter("id");
        System.out.println(status + "  "+ id);
        Service service = getPersonService();
        Person person = service.getPerson(id);
        person.setStatus(status);
        response.setHeader("Access-Control-Allow-Origin", "*");

        return "{\"inrode\" : \"ok\"}";
    }
}
