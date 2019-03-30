package djc.messageboard.Entity;


import lombok.Data;

@Data
public class UserInfoEntity{
    private int user_id;
    private String user_name;
    private String user_photo_url;
    private String password;

   public UserInfoEntity() {
    }


    public UserInfoEntity( String user_name,String user_photo_url, String password) {
        this.user_name = user_name;
        this.password = password;
        this.user_photo_url = user_photo_url;
    }
}
