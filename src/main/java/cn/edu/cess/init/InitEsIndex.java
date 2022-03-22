package cn.edu.cess.init;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.enterprise.Positions;
import cn.edu.cess.service.content.enterprise.IPositionsService;
import cn.edu.cess.util.ConfigUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: cn.edu.cess.init
 * @Description:初始化es数据
 * @Author: LuDeSong
 * @Date: 2021-8-24 15:10
 */
@Component
@Slf4j
public class InitEsIndex implements CommandLineRunner {

    @Autowired
    ElasticsearchRestTemplate esRestTemplate;
    @Autowired
    IPositionsService iPositionsService;

    @Override
    public void run(String... args) throws Exception {
        String search = ConfigUtil.getProperty("elasticsearch.positions.search", "false");
        if("true".equals(search)) {
            initPositions();
        }
    }

    private void initPositions() {
        QueryWrapper<Positions> qw = new QueryWrapper<>();
        qw.eq(Constant.ENABLED, true);
        List<Positions> positions = iPositionsService.list(qw);
        List<IndexQuery> queries = new ArrayList<>();
        for (Positions position : positions) {
            IndexQuery query = new IndexQueryBuilder()
                    .withIndexName(Positions.POSITIONS_IDX_NAME)
                    .withId(position.getId().toString())
                    .withObject(position)
                    .build();
            queries.add(query);
        }
        log.info("初始化职位es数据：{}", JSON.toJSONString(queries));
        try {
            esRestTemplate.bulkIndex(queries);
        } catch (Exception e) {
            log.error("初始化职位es数据出错：", e);
        }
    }
}
