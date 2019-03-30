package djc.messageboard.Service;


import djc.messageboard.Entity.MappingCommentInfoEntity;
import djc.messageboard.Mapper.MappingCommentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MappingCommentService {
    @Autowired
    MappingCommentInfoMapper mappingCommentInfoMapper;

    //加入一个评论的对应表
    public boolean  addComment(int user_id,int message_id,int comment_id){
        MappingCommentInfoEntity mappingCommentInfoEntity = new MappingCommentInfoEntity(user_id,message_id,comment_id);
        mappingCommentInfoMapper.addComment(mappingCommentInfoEntity);
        return  true;
    }
    //加入一张回复表
    public boolean  addResponse(int user_id,int message_id,int parent_comment_id,int comment_id) {
        MappingCommentInfoEntity mappingCommentInfoEntity = new MappingCommentInfoEntity(user_id,message_id,parent_comment_id,comment_id);
        mappingCommentInfoMapper.addReversion(mappingCommentInfoEntity);
        return true;
    }
    //加入一张私密评论表
    public boolean addPrivateComment(int user_id,int message_id,int comment_id,int object_user_id){
        MappingCommentInfoEntity mappingCommentInfoEntity = new MappingCommentInfoEntity(user_id,message_id,comment_id);
        mappingCommentInfoEntity.setObject_user_id(object_user_id);
        mappingCommentInfoMapper.addPrivateComment(mappingCommentInfoEntity);
        return  true;
    }

    //加入一张私密评论表
    public boolean addPrivateResponse(int user_id,int message_id,int parent_comment_id,int comment_id,int object_user_id){
        MappingCommentInfoEntity mappingCommentInfoEntity = new MappingCommentInfoEntity(user_id,message_id,parent_comment_id,comment_id,object_user_id);
        mappingCommentInfoMapper.addPrivateResponse(mappingCommentInfoEntity);
        return  true;
    }

    public MappingCommentInfoEntity select(int comment_id){
        return mappingCommentInfoMapper.selectById(comment_id);
    }

}
