package djc.messageboard.Service;

import djc.messageboard.Entity.CommentInfoEntity;
import djc.messageboard.Mapper.CommentInfoMapper;
import djc.messageboard.Mapper.MessageInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService {
    @Autowired
    CommentInfoMapper commentInfoMapper;
    @Autowired
    MappingCommentService mappingCommentService;
    @Autowired
    MessageInfoMapper messageInfoMapper;


    //加入一个评论
    public boolean addComment(int user_id, int message_id, String content, Date date) {             //不包含传图片
        CommentInfoEntity commentInfoEntity = new CommentInfoEntity(content, date);
        commentInfoMapper.add(commentInfoEntity);
        return mappingCommentService.addComment(user_id, message_id, commentInfoEntity.getComment_id());

    }


    public boolean addUnknownComment(int message_id, String content, Date date) {             //不包含传图片
        CommentInfoEntity commentInfoEntity = new CommentInfoEntity(content, date);
        commentInfoMapper.add(commentInfoEntity);
        return mappingCommentService.addComment(-1, message_id, commentInfoEntity.getComment_id());
    }

    //加入一个回复
    public boolean addResponse(int user_id, int message_id, int parent_comment_id, String content, Date date) {
        CommentInfoEntity commentInfoEntity = new CommentInfoEntity(content, date);
        commentInfoMapper.add(commentInfoEntity);
        return mappingCommentService.addResponse(user_id, message_id, parent_comment_id, commentInfoEntity.getComment_id());
    }

    //加入一个回复
    public boolean addUnknownResponse(int message_id, int parent_comment_id, String content, Date date) {
        CommentInfoEntity commentInfoEntity = new CommentInfoEntity(content, date);
        commentInfoMapper.add(commentInfoEntity);
        return mappingCommentService.addResponse(-1, message_id, parent_comment_id, commentInfoEntity.getComment_id());
    }

    //加入一张私人评论
    public boolean addPrivateComment(int user_id, int message_id, String content, Date date) {
        CommentInfoEntity commentInfoEntity = new CommentInfoEntity(content, date);
        commentInfoMapper.add(commentInfoEntity);
        int object_user_id = messageInfoMapper.selectUserId(message_id);
        return mappingCommentService.addPrivateComment(user_id, message_id, commentInfoEntity.getComment_id(), object_user_id);
    }

    //加入一张私人回复
    public boolean addPrivateResponse(int user_id, int message_id, int parent_comment_id, String content, Date date) {
        CommentInfoEntity commentInfoEntity = new CommentInfoEntity(content, date);
        commentInfoMapper.add(commentInfoEntity);
        int object_user_id = messageInfoMapper.selectUserId(parent_comment_id);
        return mappingCommentService.addPrivateResponse(user_id, message_id, parent_comment_id, commentInfoEntity.getComment_id(), object_user_id);
    }

    public CommentInfoEntity select(int comment_id){
        return commentInfoMapper.select(comment_id);
    }

    public int selectUserId(int comment_id) {
        return  mappingCommentService.select(comment_id).getUser_id();
    }

    public void delete(int comment_id) {
        commentInfoMapper.delete(comment_id);
    }

}
