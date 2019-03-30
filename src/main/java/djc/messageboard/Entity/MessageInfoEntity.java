package djc.messageboard.Entity;

import lombok.Data;

import java.util.Date;

@Data
public class MessageInfoEntity {

    private int message_id;
    private String message_content;
    private String message_photo_url;
    private Date   message_time;
    private int user_id;



    public MessageInfoEntity(){}

    public  MessageInfoEntity(int user_id,String message_content ,Date message_time){
        this.message_content = message_content;
        this.message_photo_url = "";
        this.message_time = message_time;
        this.user_id =user_id;
    }
}
