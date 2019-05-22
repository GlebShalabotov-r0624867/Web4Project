package domain;

public class Bericht {

    String zender;
    String ontvanger;
    String bericht;

    public Bericht(String zender, String ontvanger, String bericht) {
        setZender(zender);
        setOntvanger(ontvanger);
        setBericht(bericht);
    }


    public String getZender() {
        return this.zender;
    }

    public void setZender(String zender) {
        this.zender = zender;
    }

    public String getOntvanger() {
        return this.ontvanger;
    }

    public void setOntvanger(String ontvanger) {
        this.ontvanger = ontvanger;
    }

    public String getBericht() {
        return this.bericht;
    }

    public void setBericht(String bericht) {
        this.bericht = bericht;
    }

 /*   public String genereerJson() {

        StringBuffer json = new StringBuffer();

        json.append("{ \"zender\":\"");
        json.append(zender);
        json.append("\", \"ontvanger\":\"");
        json.append(ontvanger);
        json.append("\", \"chat\":\"");
        json.append(bericht);
        json.append("\" }");

        return json.toString();
    }*/
}
