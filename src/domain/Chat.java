package domain;

import java.util.ArrayList;

public class Chat {
    ArrayList<Bericht> chat = new ArrayList<Bericht>();

    public void addBericht(Bericht bericht){
        chat.add(bericht);
    }
//
//    public String chatToJson(){
//        StringBuffer json = new StringBuffer();
//
//        json.append("{\"chat\":");
//
//        json.append("[");
//
//        json.append( chat.get(0).genereerJson());
//
//        for( int i = 1; i < chat.size(); i++) {
//            json.append(",");
//            json.append(chat.get(i).genereerJson());
//        }
//        json.append("]}");
//
//        return json.toString();
//
//    }
}
