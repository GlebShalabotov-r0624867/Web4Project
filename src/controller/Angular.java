package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Bericht;
import domain.Person;
import domain.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Angular extends AsynchroonRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Service serivce = getPersonService();
        String json = personToJSON(serivce.getPersons());
        response.setHeader("Access-Control-Allow-Origin", "*");

        return json;
    }

    public String personToJSON (List<Person> personen) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(personen);
    }
}
