package com.engure.spider.service;

import com.engure.spider.model.OriginBook;
import com.engure.spider.service.spider.DefaultSpider;
import com.engure.spider.service.spider.observers.ObserverA;
import com.engure.spider.service.spider.pipeline.CallablePipeline;
import com.engure.spider.service.spider.strategy.IteratorProcessor;
import com.engure.spider.service.spider.observers.ObserverB;
import com.engure.spider.service.spider.observers.ObserverC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by nowcoder on 2018/08/16 下午5:23
 */
@Service
public class SpiderService {

    @Autowired
    private DefaultSpider defaultSpider;

    @Autowired
    private CallablePipeline pipeline;

    public void getLotsOfBooks(String beginUrl) {
        try {

            pipeline.addObserver(new ObserverA());
            pipeline.addObserver(new ObserverB());
            pipeline.addObserver(new ObserverC());
            defaultSpider.setProcessStrategy(new IteratorProcessor());//迭代爬取策略
            defaultSpider.getSpider()//获取爬虫实体
                    .addUrl(beginUrl)
                    .addPipeline(pipeline)
                    .thread(1)
                    .run();
            OriginBook book = (OriginBook) pipeline.getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
