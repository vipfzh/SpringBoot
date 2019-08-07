package cn.vipfzh.server.elasticsearch.dao;

import cn.vipfzh.server.entity.OrgVo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * OrgRepository interface
 *
 * @author FengZhihao
 * @date 2019/8/6 15:58
 */
public interface OrgRepository extends ElasticsearchRepository<OrgVo,String> {
}
