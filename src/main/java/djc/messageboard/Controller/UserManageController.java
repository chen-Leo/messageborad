package djc.messageboard.Controller;


import djc.messageboard.Entity.Json.User;
import djc.messageboard.Service.UserService;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/user")
public class UserManageController {


//    @Value("${web.upload-path}")
//    private String webUploadPath;

    @Autowired
    UserService UserService;


    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Object add(String username, String password) {
        User user;
        try {
            user = UserService.userAdd(username, password);
        } catch (Exception e) {
            return "name is used";
        }
        return user;

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(HttpServletRequest request, String username, String password) throws Exception {
        if (UserService.isRightPassword(username, password)) {
            User user = UserService.userFind(username);
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(15 * 60);//设置有效期为15分钟
            //把用户数据保存在session域对象中
            session.setAttribute("user", user);
            return user;
        }
        return false;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public String delete(HttpServletRequest request) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) return "you do not have the permission";
            else UserService.delete(user.getUser_id());
            return "ok";
        }

//    @RequestMapping(value = "/fileUpload" , method = RequestMethod.POST)
//    public Object fileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException {
//
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        if (user == null) return "you are not login in ";
//
//        if (!file.isEmpty()) {
//            if (file.getContentType().contains("image")) {
//                String temp = "images" + File.separator + "upload" + File.separator;
//                // 获取图片的文件名
//                String fileName = file.getOriginalFilename();
//                // 新的图片文件名 = 获取时间戳+"."图片扩展名
//                String newFileName = UUID.randomUUID() + "_" + fileName;
//                // 数据库保存的目录
//                String dateDirectory = temp.concat(String.valueOf(user.getUser_id())).concat(File.separator);
//                // 文件路径
//                String filePath = webUploadPath.concat(dateDirectory);
//
//                File dest = new File(filePath, newFileName);
//                if (!dest.getParentFile().exists()) {
//                    dest.getParentFile().mkdirs();
//                }
//                file.transferTo(dest);
//                UserService.updateUserPhoto(filePath, user.getUser_id());
//            }
//        }
//    return "ok";}
}

