package com.engure.spider.service.spider.strategy;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;

/**
 * IteratorProcessor Description: CreateDate: 2018/8/15
 *
 * @author nowcoder
 */

/**
 * 装饰者，用来装饰book的迭代爬取功能
 */
public class IteratorProcessor extends FilterProcessor {

    private Set<String> requestCache = new HashSet<>(32);

    //默认使用 OneBookProcessor 策略
    public IteratorProcessor() {
        super(new OneBookProcessor());
    }

    //指定策略
    public IteratorProcessor(ProcessStrategy processStrategy) {
        super(processStrategy);
    }

    @Override
    public void doProcess(Page page) {
        //图书列表
        if (StringUtils.startsWith(page.getRequest().getUrl(), "https://book.douban.com/tag/")) {
            requestCache.addAll(page.getHtml().regex("https://book.douban.com/subject/[0-9]+/?").all());
            page.addTargetRequests(new ArrayList<>(requestCache));
            requestCache.clear();
            page.setSkip(true);
        } else {
            //一本书的数据处理
            processStrategy.doProcess(page);
        }
    }
}
