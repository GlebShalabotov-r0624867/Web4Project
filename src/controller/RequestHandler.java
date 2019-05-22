package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Service;
import domain.Person;
import domain.Role;

import java.io.IOException;

public abstract class RequestHandler {
	
	private Service personService;
	
	public abstract String handleRequest (HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	public void setModel (Service personService) {
		this.personService = personService;
	}

	public Service getPersonService() {
		return personService;
	}
	
	protected boolean isFromUserWithRole (HttpServletRequest request, Role role) {
		Person person = (Person) request.getSession().getAttribute("user");
		if (person != null && person.getRole().equals(role)) {
			return true;
		}
		return false;
	}

}
