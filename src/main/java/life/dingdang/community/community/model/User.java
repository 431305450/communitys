package life.dingdang.community.community.model;

import lombok.Data;

@Data
public class User {
    private  String name;
    private  String accountId;
    private  Integer id;
    private  String token;
    private  long gmtCreate;
    private  long gmtModified;
    private  String avatarUrl;


}
