package djc.messageboard.Mapper;



import djc.messageboard.Entity.LikeInfoEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LikeInfoMapper {

    @Insert("INSERT INTO like_info (message_id,user_id,is_like) VALUES(#{message_id},#{user_id},#{is_like})")
    @Options(useGeneratedKeys = true, keyProperty = "like_info_id", keyColumn = "like_info_id")
    void add(LikeInfoEntity likeInfoEntity);

    @Select("SELECT is_Like FROM like_info WHERE user_id = #{user_id} AND message_id  = #{message_id}")
    Object selectIsLike(int user_id,int message_id);

    @Update("UPDATE like_info SET is_like = #{is_like}  WHERE user_id = #{user_id} AND message_id  = #{message_id} ")
    void likeChange(int is_like,int user_id,int message_id);
}
