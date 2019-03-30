package djc.messageboard.Controller;

import djc.messageboard.Entity.Json.User;
import djc.messageboard.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public Object addComment(HttpServletRequest request, int message_id, String comment_content) {
        Date date = new Date();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) return "you are not login in ";
        return commentService.addComment(user.getUser_id(), message_id, comment_content, date);
    }

    ///.......
    @RequestMapping(value = "/addResponse", method = RequestMethod.POST)
    public Object addResponse(HttpServletRequest request, int message_id, int comment_id, String comment_content) {
        Date date = new Date();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) return "you are not login in ";
        return commentService.addResponse(user.getUser_id(), message_id, comment_id, comment_content, date);
    }

    @RequestMapping(value = "/addPrivateComment", method = RequestMethod.POST)
    public Object addPrivateComment(HttpServletRequest request, int message_id, String comment_content) {
        Date date = new Date();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) return "you are not login in ";
        return commentService.addPrivateComment(user.getUser_id(), message_id, comment_content, date);
    }

    @RequestMapping(value = "/addPrivateResponse", method = RequestMethod.POST)
    public Object addPrivateResponse(HttpServletRequest request, int message_id, int comment_id, String comment_content) {
        Date date = new Date();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) return "you are not login in ";
        return commentService.addPrivateResponse(user.getUser_id(), message_id, comment_id, comment_content, date);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request, int comment_id) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) return "you do not have the permission";
        else if (user.getUser_id() != commentService.selectUserId(comment_id)) return "you do not have the permission";
        else commentService.delete(comment_id);
        return "ok";
    }

    @RequestMapping(value = "/addUnknownComment", method = RequestMethod.POST)
    public Object addUnknownComment(int message_id, String comment_content) {
        Date date = new Date();
        return commentService.addComment(-1, message_id, comment_content, date);
    }

    @RequestMapping(value = "/ addUnknownResponse", method = RequestMethod.POST)
    public Object addUnknownResponse(int message_id, int comment_id, String comment_content) {
        Date date = new Date();
        return commentService.addResponse(-1, message_id, comment_id, comment_content, date);
    }
}
