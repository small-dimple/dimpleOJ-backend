package com.dimple.dimpleOJ.esdao;

import com.dimple.dimpleOJ.model.dto.post.PostEsDTO;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 帖子 ES 操作
 *
  * @author 程序员dimple
 */
public interface PostEsDao extends ElasticsearchRepository<PostEsDTO, Long> {

    List<PostEsDTO> findByUserId(Long userId);
}