package djc.messageboard.Entity;

import lombok.Data;

@Data
public class LikeInfoEntity {
    private int like_info_id;
    private int message_id;
    private int user_id;
    private int is_like = -1;
    public LikeInfoEntity(){ }
    public LikeInfoEntity(int message_id,int user_id){
        this.message_id =  message_id;
        this.user_id = user_id;
    }
   public LikeInfoEntity(int message_id,int user_id,int is_like){
        this.message_id = message_id;
        this.user_id = user_id;
        this.is_like = is_like;
    }
}
