package com.engure.spider.demo.spider.strategy.impl;

import com.engure.spider.demo.spider.strategy.PageProcessStrategy;
import us.codecraft.webmagic.Page;

/**
 * 装饰类，伪实现类，原有类的包装类
 */

public class DecoratorPageProcessStrategy implements PageProcessStrategy {

    private PageProcessStrategy pageProcessStrategy;

    //指定策略
    public DecoratorPageProcessStrategy(PageProcessStrategy pageProcessStrategy) {
        this.pageProcessStrategy = pageProcessStrategy;
    }

    //“最顶层”的页面处理
    @Override
    public void doProcess(Page page) {
        pageProcessStrategy.doProcess(page);
    }
}
