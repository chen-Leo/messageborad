package djc.messageboard.Mapper;


import djc.messageboard.Entity.Json.User;
import djc.messageboard.Entity.UserInfoEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserInfoMapper {

    @Insert("INSERT INTO user_info(user_name,password) VALUES(#{user_name},#{password})")
    @Options(useGeneratedKeys = true,keyColumn = "user_id",keyProperty = "user_id")
    void add(UserInfoEntity userInfoEntity);

    @Select("SELECT * FROM user_info WHERE user_id = #{user_id}")
    UserInfoEntity selectById(int user_id);

    @Select("SELECT * FROM user_info WHERE user_name = #{user_name}")
    UserInfoEntity selectByName(String user_name);

    @Update("UPDATE user_info SET user_photo_url = #{user_photo_url} WHERE user_id = #{user_id}")
    int updatePhoto(String user_photo_url,int user_id);

    @Delete("DELETE  FROM user_info where user_id = #{user_id}")
    void delete(int user_id);
}
