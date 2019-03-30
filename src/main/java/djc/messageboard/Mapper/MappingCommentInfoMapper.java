package djc.messageboard.Mapper;


import djc.messageboard.Entity.MappingCommentInfoEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface MappingCommentInfoMapper {


    //加入评论
    @Insert("INSERT INTO mapping_comment_info (user_id,message_id,comment_id) VALUES(#{user_id},#{message_id},#{comment_id})")
    @Options(useGeneratedKeys = true, keyProperty = "mapping_id", keyColumn = "mapping_id")
    void addComment(MappingCommentInfoEntity mappingCommentInfoEntity);

    //加入回复
    @Insert("INSERT INTO mapping_comment_info (user_id,message_id,parent_comment_id,comment_id) VALUES(#{user_id},#{message_id},#{parent_comment_id},#{comment_id})")
    @Options(useGeneratedKeys = true, keyProperty = "mapping_id", keyColumn = "mapping_id")
    void addReversion(MappingCommentInfoEntity mappingCommentInfoEntity);

    //加入私密评论
    @Insert("INSERT INTO mapping_comment_info (user_id,message_id,comment_id,object_user_id) VALUES(#{user_id},#{message_id},#{comment_id},#{object_user_id)")
    @Options(useGeneratedKeys = true, keyProperty = "mapping_id", keyColumn = "mapping_id")
    void addPrivateComment(MappingCommentInfoEntity mappingCommentInfoEntity);

    //加入私密回复
    @Insert("INSERT INTO mapping_comment_info (user_id,message_id,parent_comment_id,comment_id,object_user_id) VALUES(#{user_id},#{message_id},#{parent_comment_id},#{comment_id},#{object_user_id)")
    @Options(useGeneratedKeys = true, keyProperty = "mapping_id", keyColumn = "mapping_id")
    void addPrivateResponse(MappingCommentInfoEntity mappingCommentInfoEntity);

    @Select("SELECT comment_id FROM mapping_comment_info WHERE message_id = #{message_id} AND parent_comment_id is not null AND ( object_user_id is null  OR object_user_id = #{object_user_id} )")
    @ResultType(List.class)
    List<Integer> selectResponseByMessageId(int message_id, int object_user_id);

    @Select("SELECT comment_id FROM mapping_comment_info WHERE message_id = #{message_id} AND parent_comment_id is null AND ( object_user_id is null  OR object_user_id = #{object_user_id})")
    @ResultType(List.class)
    List<Integer> selectCommentByMessageId(int message_id, int object_user_id);

    @Select("Select * FROM mapping_comment_info WHERE comment_id = #{comment_id}")
    MappingCommentInfoEntity selectById(int comment_id);


}
