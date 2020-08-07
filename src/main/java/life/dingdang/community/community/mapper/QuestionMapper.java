package life.dingdang.community.community.mapper;

import life.dingdang.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface QuestionMapper {
//@Insert("insert into question (title,description,create,gmt_modified,creator,comment_count,view_count,like_count,tag)values(#{title},#{description},#{tag},#{Create},#{gmtModified},#{creator},#{viewCount},#{likeCount},#{commentCount})")
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag)values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{viewCount},#{likeCount},#{commentCount},#{tag})")
    void create(Question question);
@Select("select *from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size")Integer size);
@Select("select count(1) from question")
  Integer count();
}
