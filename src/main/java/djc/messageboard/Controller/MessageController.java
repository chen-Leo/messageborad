package djc.messageboard.Controller;

import djc.messageboard.Entity.Json.Message;
import djc.messageboard.Entity.Json.User;
import djc.messageboard.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping(value = "/message")
public class MessageController {
    @Autowired
    MessageService messageService;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(HttpServletRequest request, String message_content) {
        Date date = new Date();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) return "you are not login in ";
        messageService.add(user.getUser_id(), message_content, date);
        return true;
    }

    @RequestMapping(value = "/returnAll", method = RequestMethod.POST)
    public Message[] returnMessageAll(HttpServletRequest request, int user_id) {
        int now_user_id = -1;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) now_user_id = user.getUser_id();
        return messageService.returnMessageAll(user_id, now_user_id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request, int comment_id) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) return "you do not have the permission";
        else if (user.getUser_id() != messageService.selectUserId(comment_id)) return "you do not have the permission";
        else messageService.delete(comment_id);
        return "ok";
    }

   @RequestMapping(value = "/addUnknownMessage",method = RequestMethod.POST)
   public  Object addUnknownMessage( String message_content) {
       Date date = new Date();
       messageService.addUnknownMessage(message_content, date);
       return true;
   }












}
