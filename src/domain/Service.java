package domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.BlogDB;
import db.PersonRepository;
import db.PersonRepositoryStub;

public class Service {
	private PersonRepository personRepository = new PersonRepositoryStub();
	private BlogDB blogDB = new BlogDB();
	public ArrayList<Bericht> chats = new ArrayList<Bericht>();
	public Service(){

		//berichten creeeren;
		Bericht bericht1 = new Bericht("bib@ucll.be", "jan@ucll.be", "Test 1 oud bericht van bib");
		chats.add(bericht1);
	}



	public Person getPerson(String personId)  {
		return getPersonRepository().get(personId);
	}

	public List<Person> getPersons() {
		return getPersonRepository().getAll();
	}

	public void addPerson(Person person) {
		getPersonRepository().add(person);
	}

	public void updatePersons(Person person) {
		getPersonRepository().update(person);
	}

	public void deletePerson(String id) {
		getPersonRepository().delete(id);
	}
	
	public Person getAuthenticatedUser(String email, String password) {
		return getPersonRepository().getAuthenticatedUser(email, password);
	}

	public void addNewFriend(Person user, String friendMail){

		getPersonRepository().addNewFriend(user,friendMail);
	}
	private PersonRepository getPersonRepository() {
		return personRepository;
	}

	public ArrayList<Blog> getAllBlogs(){
		return blogDB.getBlogs();
	}

	public Blog getBlogByid(int id){
		return blogDB.getBlogById(id);
	}

	public void addNewMessage (Bericht bericht){
		chats.add(bericht);
	}

	public ArrayList<Bericht> getMassagesOf(String zender, String ontvanger) throws JsonProcessingException {
		ArrayList<Bericht> ownerChat = new ArrayList<Bericht>();
		for(Bericht b:chats){
			String berichtOntvanger = b.getOntvanger();
			String berichtZender = b.getZender();
			System.out.println(berichtOntvanger + " " + ontvanger);
			System.out.println(berichtZender+ " " + zender);
			if (berichtOntvanger.equals(ontvanger) && berichtZender.equals(zender)){
				ownerChat.add(b);
				System.out.println(messageToJSON(b));
			}
			if (berichtOntvanger.equals(zender) && berichtZender.equals(ontvanger)){
				ownerChat.add(b);
				System.out.println(messageToJSON(b));
			}


		}
		return ownerChat;
	}

	public String messageToJSON (Bericht bericht) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(bericht);
	}

}
