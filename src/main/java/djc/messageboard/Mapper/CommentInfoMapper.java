package djc.messageboard.Mapper;

import djc.messageboard.Entity.CommentInfoEntity;
import org.apache.ibatis.annotations.*;
@Mapper
public interface CommentInfoMapper {
    @Insert("INSERT INTO comment_info (comment_content,comment_photo_url,comment_time) VALUES(#{comment_content},#{comment_photo_url},#{comment_time})")
    @Options(useGeneratedKeys = true, keyProperty = "comment_id", keyColumn = "comment_id")
    void add(CommentInfoEntity commentInfoEntity);

    @Select("SELECT * FROM comment_info WHERE comment_id = #{comment_id}")
    CommentInfoEntity select(int comment_id);

    @Delete("DELETE FROM comment_info WHERE comment_id = #{comment_id}")
    void  delete (int comment_id);

}
