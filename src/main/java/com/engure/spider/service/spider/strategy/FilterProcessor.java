package com.engure.spider.service.spider.strategy;

import us.codecraft.webmagic.Page;

/**
 * FilterProcessor Description: CreateDate: 2018/8/15
 *
 * @author nowcoder
 */

/**
 * 1.FilterProcessor 是 ProcessStrategy 的装饰类。
 * 2.用来扩展 ProcessStrategy 的爬虫方法。
 * 3.这是一个伪实现类，真正的装饰类需要继承这个类。
 * 设计参考 {@link java.io.FilterInputStream}
 */
public class FilterProcessor implements ProcessStrategy {

    protected volatile ProcessStrategy processStrategy;

    public FilterProcessor(ProcessStrategy processStrategy) {
        this.processStrategy = processStrategy;
    }

    @Override
    public void doProcess(Page page) {
        processStrategy.doProcess(page);
    }
}
