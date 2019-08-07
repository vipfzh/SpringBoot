package cn.vipfzh.server.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * OrgVo class
 *
 * @author FengZhihao
 * @date 2019/8/6 15:10
 */
@Data
@Document(indexName = "org",type = "text",shards = 1,replicas = 0)
public class OrgVo {

    /**
     * id
     */
    @Id
    private String id;
    /**
     * 公司名称，类型为text会分词，分词器使用ik_max_word（细颗粒度分词）
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String orgName;
    /**
     * 统一社会信用代码，类型为keyword查询时不会被分词
     */
    @Field(type = FieldType.Keyword)
    private String uniscId;
}
