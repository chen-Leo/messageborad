package djc.messageboard.Entity;

import lombok.Data;

/**
 * 这是评论及回复的映射表
 */
@Data
public class MappingCommentInfoEntity {
    private int mapping_id;
    private int user_id;
    private int message_id;
    private int parent_comment_id;  //若不为空，则表示该表为一张回复表（回复评论)
    private int comment_id; //该属性唯一，用于唯一对应的评论或回复表
    private int object_user_id;


    public MappingCommentInfoEntity() {
    }

    //加入评论
    public MappingCommentInfoEntity(int user_id, int message_id, int comment_id) {
        this.user_id = user_id;
        this.message_id = message_id;
        this.comment_id = comment_id;
    }

    //加入回复
    public MappingCommentInfoEntity(int user_id,int message_id, int parent_comment_id, int comment_id) {
        this.user_id = user_id;
        this.message_id = message_id;
        this.comment_id = comment_id;
        this.parent_comment_id = parent_comment_id;
    }



    //加入私密回复
    public MappingCommentInfoEntity(int user_id, int message_id, int parent_comment_id, int comment_id, int object_user_id) {
        this.user_id = user_id;
        this.message_id = message_id;
        this.comment_id = comment_id;
        this.parent_comment_id = parent_comment_id;
        this.object_user_id = object_user_id;
    }


}
