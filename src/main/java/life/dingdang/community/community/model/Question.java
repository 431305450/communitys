package life.dingdang.community.community.model;


import lombok.Data;
        import org.apache.ibatis.annotations.Insert;
@Data
public class Question {
    private Integer id;
    private  String title;
    private  String description;
    private  String tag;
    private  long gmtCreate;
    private  long gmtModified;
    private  Integer creator;
    private  Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;


}
