package domain;

import java.util.ArrayList;

public class Blog {
    String vraag;
    ArrayList<BlogAntwoorden> antwoorden = new ArrayList<BlogAntwoorden>();
    int id;


    public Blog(int id,String vraag) {
        setId(id);
        setVraag(vraag);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVraag() {
        return vraag;
    }

    public void setVraag(String vraag) {
        this.vraag = vraag;
    }

    public void addAwnser(BlogAntwoorden ba){
        antwoorden.add(ba);
    }

    public ArrayList<BlogAntwoorden> getAntwoorden() {
        return antwoorden;
    }

    public void setAntwoorden(ArrayList<BlogAntwoorden> antwoorden) {
        this.antwoorden = antwoorden;
    }
}
