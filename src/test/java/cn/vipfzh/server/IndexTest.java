package cn.vipfzh.server;

import cn.vipfzh.ElasticsearchApplication;
import cn.vipfzh.server.elasticsearch.dao.OrgRepository;
import cn.vipfzh.server.entity.OrgVo;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * IndexTest class
 *
 * @author FengZhihao
 * @date 2019/8/6 15:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchApplication.class)
public class IndexTest {


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private OrgRepository orgRepository;

    /**
     * 创建索引库
     */
    @Test
    public void creatIndex(){
        //创建索引库
        elasticsearchTemplate.createIndex(OrgVo.class);
        //创建索引
        elasticsearchTemplate.putMapping(OrgVo.class);
    }

    /**
     * 删除索引库
     */
    @Test
    public void delIndex(){
        elasticsearchTemplate.deleteIndex(OrgVo.class);
    }

    /**
     * 添加索引
     */
    @Test
    public void addOrg(){

        OrgVo orgVo = new OrgVo();
        orgVo.setId("123245");
        orgVo.setOrgName("我的公司");
        orgVo.setUniscId("654321");
        orgRepository.save(orgVo);
    }

    /**
     * 批量添加索引
     */
    @Test
    public void batchAddOrg(){

        List<OrgVo> orgVos = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            OrgVo orgVo = new OrgVo();
            orgVo.setId("6666666"+i);
            orgVo.setOrgName("我是一个公司"+i);
            orgVo.setUniscId("65495615694"+i);
            orgVos.add(orgVo);
        }

        orgRepository.saveAll(orgVos);
    }

    /**
     * 查询所有
     */
    @Test
    public void findAll(){
        Iterable<OrgVo> all = orgRepository.findAll();
        for (OrgVo orgVo : all) {
            System.out.println(orgVo);
        }
    }


    /**
     * 查询所有（分页）
     */
    @Test
    public void findByPage(){
        Pageable pageable = new QPageRequest(0,5);
        Page<OrgVo> all = orgRepository.findAll(pageable);
        for (OrgVo orgVo : all) {
            System.out.println(orgVo);
        }
    }

    /**
     * 添加查询（分页）
     */
    @Test
    public void findByCondition(){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.matchQuery("orgName","组织"));
        NativeSearchQuery build = queryBuilder.build();
        QueryBuilder query = build.getQuery();
        Pageable pageable = new QPageRequest(0,5);
        Page<OrgVo> search = orgRepository.search(query,pageable);
        for (OrgVo orgVo : search) {
            System.out.println(orgVo);
        }
    }
}
