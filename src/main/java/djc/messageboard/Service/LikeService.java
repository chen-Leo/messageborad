package djc.messageboard.Service;


import djc.messageboard.Entity.LikeInfoEntity;
import djc.messageboard.Mapper.LikeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    LikeInfoMapper likeInfoMapper;


    public int likeChange(int user_id, int message_id) {
        int like = selectIsLike(user_id, message_id);
        if (like == -1) {
            LikeInfoEntity likeInfoEntity = new LikeInfoEntity(message_id, user_id, 1);
            likeInfoMapper.add(likeInfoEntity);
            return 1;
        } else if (like == 1) {
            likeInfoMapper.likeChange(0, user_id, message_id);
            return 0;
        } else {
            likeInfoMapper.likeChange(1, user_id, message_id);
            return 1;
        }

    }

    public int selectIsLike(int user_id, int message_id) {
        if (likeInfoMapper.selectIsLike(user_id, message_id) == null) return -1;
        else if (new Integer(1).equals(likeInfoMapper.selectIsLike(user_id, message_id))) return 1;
        else return 0;
    }

}
