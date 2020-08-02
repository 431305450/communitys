package life.dingdang.community.community.dto;
import life.dingdang.community.community.model.User;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
public class QuestionDTO {
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
        private User user;


}
