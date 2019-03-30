package djc.messageboard.Entity;


import djc.messageboard.Utils.SafeUtil;
import lombok.Data;

import java.util.Date;

@Data
public class CommentInfoEntity {
    private int comment_id;
    private String comment_content;
    private String comment_photo_url;
    private Date   comment_time;
public CommentInfoEntity(){
    }

    public CommentInfoEntity(String comment_content, Date comment_time){
        this.comment_content = comment_content;
        this.comment_photo_url = "";
        this.comment_time = comment_time;
    }
}
