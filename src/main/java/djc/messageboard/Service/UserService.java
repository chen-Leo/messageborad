package djc.messageboard.Service;



import djc.messageboard.Entity.Json.User;
import djc.messageboard.Entity.UserInfoEntity;
import djc.messageboard.Mapper.UserInfoMapper;

import djc.messageboard.Utils.SafeUtil;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.UUID;

@Service
public class UserService {

@Autowired
UserInfoMapper userInfoMapper;

    public boolean isRightPassword(String userName, String password) throws Exception {
        String passwordMysql = null;
        passwordMysql = userInfoMapper.selectByName(userName).getPassword();
        if (passwordMysql.equals(SafeUtil.EncryptPassword(password))) {
            return true;
        } else return false;
    }

    public User userAdd(String username, String password) throws Exception {
        password = SafeUtil.EncryptPassword(password);
        UserInfoEntity userInfoEntity =  new UserInfoEntity(username,null,password);
        userInfoMapper.add(userInfoEntity);
        return   new User(userInfoEntity.getUser_id(),userInfoEntity.getUser_photo_url(),userInfoEntity.getUser_name());
    }

    public  User userFind(String username) {
        UserInfoEntity userInfoEntity = userInfoMapper.selectByName(username);
        return   new User(userInfoEntity.getUser_id(),userInfoEntity.getUser_photo_url(),userInfoEntity.getUser_name());
    }

public  boolean updateUserPhoto(String path,int user_id){
       if (userInfoMapper.updatePhoto(path,user_id) ==1)
        return true;
       else  return false;
}

public void delete(int user_id){
        userInfoMapper.delete(user_id);
}




}
