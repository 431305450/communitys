package life.dingdang.community.community.mapper;

import life.dingdang.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface QuestionMapper {
//@Insert("insert into question (title,description,create,gmt_modified,creator,comment_count,view_count,like_count,tag)values(#{title},#{description},#{tag},#{Create},#{gmtModified},#{creator},#{viewCount},#{likeCount},#{commentCount})")
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag)values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{viewCount},#{likeCount},#{commentCount},#{tag})")
    void create(Question question);
}
