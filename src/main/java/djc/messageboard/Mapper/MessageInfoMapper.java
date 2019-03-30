package djc.messageboard.Mapper;

import djc.messageboard.Entity.MessageInfoEntity;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface MessageInfoMapper {
    @Insert("INSERT INTO message_info (message_content,message_photo_url,message_time,user_id) VALUES(#{message_content},#{message_photo_url},#{message_time},#{user_id})")
    @Options(useGeneratedKeys = true, keyProperty = "message_id", keyColumn = "message_id")
    void add(MessageInfoEntity messageInfoEntity);

    @Select("SELECT * FROM message_info WHERE user_id = #{user_id}")
    @ResultType(List.class)
    List<MessageInfoEntity> selectByUserId(int user_id);

    @Select("SELECT user_id FROM message_info WHERE message_id = #{message_id}")
    int selectUserId(int message_id);

    @Delete("DELETE FROM message_info WHERE message_id = #{message_id}")
    void delete(int message_id);
}
