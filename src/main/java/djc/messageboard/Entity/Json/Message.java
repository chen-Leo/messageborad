package djc.messageboard.Entity.Json;


import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class Message {

    private int user_id;
    private int message_id;
    private String message_content;
    private String message_photo_url;
    private Date   message_time;
    private ArrayList<Comment> comments;
    boolean is_like;

    public Message(){}
    public Message(int user_id,int message_id,String message_content ,Date message_time,boolean is_like){
        this.user_id = user_id;
        this.message_id = message_id;
        this.message_content = message_content;
        this.message_time = message_time;
        this.is_like = is_like;
        comments =  new ArrayList<>();
   }

    public void add(Comment comment) {
        if (comment != null) {
            comments.add(comment);
        }
    }
}
