package djc.messageboard.Controller;


import djc.messageboard.Entity.Json.User;
import djc.messageboard.Service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping(value = "/message")
public class LikeController {

    @Autowired
    LikeService likeService;

    @RequestMapping(value = "/likeChange", method = RequestMethod.POST)
    public String likeChange(HttpServletRequest request, int message_id) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) return "you are not login in ";
        int result = likeService.likeChange(user.getUser_id(), message_id);
        if (result == 1) return "点赞成功";
        else return "取消点赞";
    }
}
