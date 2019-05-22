package domain;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MakingFriends {

    private ArrayList<Person> lijst = new ArrayList<>();


    public ArrayList<Person> getAllFriends(){
        return lijst;
    }

    public  void addPerson( Person person ){
        lijst.add(person);
    }

    public Person get(int plaats) {
        return lijst.get(plaats);
    }


}
