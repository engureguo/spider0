package com.engure.spider.demo.spider.strategy.impl;

import com.engure.spider.demo.spider.strategy.PageProcessStrategy;
import us.codecraft.webmagic.Page;

public class IteratorPageProcessStrategy extends DecoratorPageProcessStrategy {

    //默认单页爬取策略
    public IteratorPageProcessStrategy() {
        super(new BookNavPageProcessStrategy());
    }

    //指定爬取策略
    public IteratorPageProcessStrategy(PageProcessStrategy pageProcessStrategy) {
        super(pageProcessStrategy);
    }

    /**
     * 迭代爬取实现，对不同页面进行不同的爬取操作。
     * 对原有的单页处理做拓展。
     *
     * @param page
     */
    @Override
    public void doProcess(Page page) {
        String url = page.getRequest().getUrl();
        if (url.startsWith("https://book.douban.com/tag/")) {
            //单页爬取
            super.doProcess(page);
        } else if (url.startsWith("https://book.douban.com/subject/[0-9]+")) {
            //跳过此页的数据处理
            page.setSkip(true);
        }
    }
}
