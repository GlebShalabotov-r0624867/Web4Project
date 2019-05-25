package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.MakingFriends;
import domain.Person;
import domain.Role;

public class PersonRepositoryStub implements PersonRepository {
    private Map<String, Person> persons = new HashMap<String, Person>();

    public PersonRepositoryStub() {

        Person administrator = new Person("bib@ucll.be", "t", "Bib", "Liothekaris", Role.BIB);

        add(administrator);
        Person jan = new Person("jan@ucll.be", "t", "Jan", "Janssens", Role.LID);
        add(jan);
        Person an = new Person("an@ucll.be", "t", "An", "Cornelissen", Role.LID);
        add(an);

        Person jean = new Person("jean@ucll.be", "t", "Jean", "DikHOOFD", Role.LID);
        add(jean);

        MakingFriends list = new MakingFriends();
        list.addPerson(administrator);
        list.addPerson(an);

        MakingFriends list2 = new MakingFriends();
        list2.addPerson(jan);

        MakingFriends list3 = new MakingFriends();
        list3.addPerson(jan);

        jan.setVriendenlijst(list);
        administrator.setVriendenlijst(list2);
        an.setVriendenlijst(list3);

    }

    public Person get(String personId) {
        if (personId == null) {
            throw new IllegalArgumentException("No id given");
        }
        return persons.get(personId);
    }

    public List<Person> getAll() {
        return new ArrayList<Person>(persons.values());
    }

    public void add(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("No person given");
        }
        if (persons.containsKey(person.getUserId())) {
            throw new IllegalArgumentException("User already exists");
        }
        persons.put(person.getUserId(), person);
    }

    public void update(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("No person given");
        }
        persons.put(person.getUserId(), person);
    }

    public void delete(String personId) {
        if (personId == null) {
            throw new IllegalArgumentException("No id given");
        }
        persons.remove(personId);
    }

    public Person getAuthenticatedUser(String email, String password) {
        Person person = get(email);

        if (person != null && person.isCorrectPassword(password)) {
            return person;
        } else {
            return null;
        }
    }

    public void addNewFriend(Person user, String friendMail) {
        if (persons.containsKey(friendMail)) {
            Person friendPerson = persons.get(friendMail);
            user.addFriend(friendPerson);
            friendPerson.addFriend(user);
        }
    }
}
