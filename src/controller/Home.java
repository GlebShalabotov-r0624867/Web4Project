package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Home extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Person man =  (Person) session.getAttribute("user");
        request.setAttribute("user", man);
        request.setAttribute("blogs",getPersonService().getAllBlogs());
        return "index.jsp";
    }
}
