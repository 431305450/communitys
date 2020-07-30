package life.dingdang.community.community.controller;

import life.dingdang.community.community.dto.AccessTokenDTO;
import life.dingdang.community.community.dto.GithubUser;
import life.dingdang.community.community.mapper.UserMapper;
import life.dingdang.community.community.model.User;
import life.dingdang.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;


@Controller
public class AuthorizeController {


    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUrl;


    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){//把上下文中的request放在request供我们使用
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
//        githubProvider.getAccessToken(accessTokenDTO);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser guser = githubProvider.getUser(accessToken);
        if(guser!=null){//如果登录不为空，写Cookie和session,否则登陆失败
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(guser.getName());
            user.setAccountId(String.valueOf(guser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);//把user写入数据库便相当于写入session
            //登陆成功，写入cookie和session
            response.addCookie(new Cookie("token",token));
           // request.getSession().setAttribute("guser",guser);//把user对象放到session里面
            return "redirect:index";
        }else{
            return "redirect:index"; //重新登录
        }

    }
}