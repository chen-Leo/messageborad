package djc.messageboard.Service;


import djc.messageboard.Entity.CommentInfoEntity;
import djc.messageboard.Entity.Json.Comment;
import djc.messageboard.Entity.Json.Message;

import djc.messageboard.Entity.MessageInfoEntity;
import djc.messageboard.Mapper.CommentInfoMapper;
import djc.messageboard.Mapper.MappingCommentInfoMapper;
import djc.messageboard.Mapper.MessageInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageInfoMapper messageInfoMapper;
    @Autowired
    MappingCommentInfoMapper mappingCommentInfoMapper;
    @Autowired
    CommentInfoMapper commentInfoMapper;
    @Autowired
    LikeService likeService;


    public void add(int user_id, String content, Date date) {             //不包含传图片
        MessageInfoEntity messageInfoEntity = new MessageInfoEntity(user_id, content, date);
        messageInfoMapper.add(messageInfoEntity);
    }


    public Message[] returnMessageAll(int user_id, int now_user_id) {
        List<MessageInfoEntity> messageInfoEntities = messageInfoMapper.selectByUserId(user_id);  //所有符合要求的message 实体
        ArrayList<Message> messages = new ArrayList<>();
        if (messageInfoEntities != null) {
            for (MessageInfoEntity messageInfoEntity :
                    messageInfoEntities) {
                int like = -1;
                boolean is_like = false;
                if (now_user_id != -1) like = likeService.selectIsLike(user_id, messageInfoEntity.getMessage_id());
                if (like == 1) is_like = true;
                Message message = new Message(messageInfoEntity.getUser_id(), messageInfoEntity.getMessage_id(), messageInfoEntity.getMessage_content(), messageInfoEntity.getMessage_time(), is_like);
                ArrayList<Comment> comments = new ArrayList<>();
                ArrayList<Comment> responses = new ArrayList<>();
                //所有符合要求的评论表Id
                List<Integer> commentIds = mappingCommentInfoMapper.selectCommentByMessageId(messageInfoEntity.getMessage_id(), now_user_id);
                addComment(commentIds, comments);
                //所有符合要求的回复表Id
                List<Integer> responseIds = mappingCommentInfoMapper.selectResponseByMessageId(messageInfoEntity.getMessage_id(), now_user_id);
                addResponse(responseIds, responses);

                for (Comment comment :
                        comments) {
                    getComments(comment, responses);

                }
                for (Comment comment : comments) {
                    message.add(comment);
                }
                messages.add(message);
            }
        }
        return messages.toArray(new Message[messages.size()]);
    }


    public void getComments(Comment allComments, ArrayList<Comment> Responses) {

        int i = 0;
        int length = Responses.size();
        int goalId = allComments.getComment_id();
        while (i < length && length != 0) {
            if (goalId == Responses.get(i).getParentComment_id()) {
                allComments.add(Responses.get(i));
                getComments(Responses.get(i), Responses);
            }
            i++;
        }
    }

    public void addComment(List<Integer> commentIds, ArrayList<Comment> comments) {
        for (int comment_id :
                commentIds) {
            CommentInfoEntity commentInfoEntity = commentInfoMapper.select(comment_id);
            Comment comment = new Comment(mappingCommentInfoMapper.selectById(comment_id).getUser_id(), commentInfoEntity.getComment_id(), commentInfoEntity.getComment_content(), commentInfoEntity.getComment_time());
            comments.add(comment);
        }
    }


    public void addResponse(List<Integer> commentIds, ArrayList<Comment> comments) {
        for (int comment_id :
                commentIds) {
            CommentInfoEntity commentInfoEntity = commentInfoMapper.select(comment_id);
            Comment comment = new Comment(mappingCommentInfoMapper.selectById(comment_id).getUser_id(), mappingCommentInfoMapper.selectById(comment_id).getParent_comment_id(), commentInfoEntity.getComment_id(), commentInfoEntity.getComment_content(), commentInfoEntity.getComment_time());
            comments.add(comment);
        }
    }

    public void delete(int message_id) {
        messageInfoMapper.delete(message_id);
    }

    public int selectUserId(int message_id) {
        return messageInfoMapper.selectUserId(message_id);
    }

    public void addUnknownMessage(String content, Date date){
        MessageInfoEntity messageInfoEntity = new MessageInfoEntity(-1, content, date);
        messageInfoMapper.add(messageInfoEntity);
    }
}


