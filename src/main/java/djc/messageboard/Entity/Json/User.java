package djc.messageboard.Entity.Json;


import lombok.Data;

@Data
public class User {

    private int user_id;
    private String user_name;
    private String user_photo_url;

    User(){}
    public User(int user_id,String user_photo_url, String user_name) {
        this.user_id = user_id;
        this.user_photo_url = user_photo_url;
        this.user_name = user_name;
    }
}


