package life.dingdang.community.community.controller;

import life.dingdang.community.community.dto.PaginationDTO;
import life.dingdang.community.community.dto.QuestionDTO;
import life.dingdang.community.community.mapper.QuestionMapper;
import life.dingdang.community.community.mapper.UserMapper;
import life.dingdang.community.community.model.Question;
import life.dingdang.community.community.model.User;
import life.dingdang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired

    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request,Model model,
                        //获取页面页数参数
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size
                        ){
        Cookie[] cookies =request.getCookies();
        if(cookies!=null&&cookies.length!=0)
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("token")){
                String token=cookie.getValue();
                User user =userMapper.findByToken(token);
                if(user!=null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        PaginationDTO pagination=questionService.list(page, size);


        model.addAttribute("pagination", pagination);
        return "index";
    }
}