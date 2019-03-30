package djc.messageboard.Entity.Json;


import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class Comment {
    private int user_id;
    private int parentComment_id;
    private int comment_id;
    private String comment_content;
    private String comment_photo_url;
    private Date message_time;
    private ArrayList<Comment> response;


    public Comment() {
        response = new ArrayList<>();
    }

    //构建一个评论表
    public Comment(int user_id,int comment_id, String comment_content,Date message_time) {
        this.user_id = user_id;
        this.comment_content = comment_content;
        this.comment_id = comment_id;
        response = new ArrayList<>();
        this.message_time = message_time;
    }
//构建一张回复表
    public Comment(int user_id,int parentComment_id, int comment_id, String comment_content,Date message_time) {
        this.user_id = user_id;
        this.comment_content = comment_content;
        this.comment_id = comment_id;
        this.parentComment_id = parentComment_id;
        response = new ArrayList<>();
        this.message_time = message_time;

    }

    public void add(Comment comment) {
        if (comment != null) {
            response.add(comment);
        }
    }
}
