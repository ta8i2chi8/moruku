package com.morimoto.taichi.moruku.persistence.mapper;

import com.morimoto.taichi.moruku.persistence.entity.Question;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface QuestionMapper {
    @Select("""
        SELECT 
            q.id, q.content, q.image_content, q.attention_count, q.created_at 
        FROM questions AS q
        WHERE q.id = #{id} 
        GROUP BY q.id, q.content, q.image_content, q.attention_count, q.created_at 
    """)
    Question findById(Integer id);
    
    @Select("""
        SELECT 
            q.id, q.content, q.attention_count, q.created_at 
        FROM questions AS q 
        GROUP BY q.id, q.content, q.attention_count, q.created_at 
        ORDER BY q.created_at DESC
    """)
    List<Question> findAll();

    @Select("""
        SELECT 
            q.id, q.content, q.attention_count, q.created_at 
        FROM questions AS q 
        GROUP BY q.id, q.content, q.attention_count, q.created_at 
        ORDER BY q.attention_count DESC, q.created_at DESC
    """)
    List<Question> findAllSortByAttention();

    @Select("""
        SELECT 
            q.id, q.content, q.attention_count, q.created_at 
        FROM questions AS q 
        GROUP BY q.id, q.content, q.attention_count, q.created_at 
        ORDER BY q.created_at DESC
    """)
    List<Question> findAllSortByAnswerCount();

    @Select("""
        <script>
            SELECT 
                q.id, q.content, q.attention_count, q.created_at 
            FROM questions AS q 
            WHERE 
                <foreach item='query' collection='queries' separator='AND'>
                    q.content LIKE CONCAT('%', #{query}, '%')
                </foreach>
            GROUP BY q.id, q.content, q.attention_count, q.created_at 
            ORDER BY created_at DESC
        </script>
    """)
    List<Question> search(String[] queries);

    @Insert("INSERT INTO questions (content, image_content) VALUES (#{content}, #{imageContent})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(Question question);
    
    @Update("UPDATE questions SET attention_count = attention_count + 1 WHERE id = #{id}")
    void attend(Integer id);

    @Delete("DELETE FROM questions WHERE id = #{id}")
    void delete(Integer id);
}
