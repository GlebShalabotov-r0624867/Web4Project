package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeStatus extends AsynchroonRequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Person man = (Person) session.getAttribute("user");
        String status = request.getParameter("newStatus");
        System.out.println(man.getStatus());
        man.setStatus( status);
        System.out.println(status);


        return status;



    }
}
