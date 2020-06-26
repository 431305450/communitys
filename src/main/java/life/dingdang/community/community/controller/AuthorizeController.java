package life.dingdang.community.community.controller;

import life.dingdang.community.community.dto.AccessTokenDTO;
import life.dingdang.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @GetMapping("/callback")
    //第一步调用Authorize接口接受传过来的code stateo从页面传过来的
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setClient_id("0fed175c7fd06404848b");
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_secret("a66dc50a77a48221e25aeadd9c360caeaed0e462");
        githubProvider.getAccessToken(accessTokenDTO);
        return  "index";
    }
}
