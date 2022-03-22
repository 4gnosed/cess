package cn.edu.cess.config;

import cn.edu.cess.util.ConfigUtil;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

/**
 * @Package: cn.edu.cess.config
 * @Description:
 * @Author: LuDeSong
 * @Date: 2021-8-24 10:23
 */
@Configuration
@DependsOn("configUtil")
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        String property = ConfigUtil.getProperty("elasticsearch.hostandport", "localhost:9200");
        ClientConfiguration cc = ClientConfiguration.builder()
                .connectedTo(property)
                .build();
        return RestClients.create(cc).rest();
    }

    @Bean
    public ElasticsearchRestTemplate elasticsearchRestTemplate() {
        return new ElasticsearchRestTemplate(elasticsearchClient());
    }
}
