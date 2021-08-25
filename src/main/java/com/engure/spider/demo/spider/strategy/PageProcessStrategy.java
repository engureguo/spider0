package com.engure.spider.demo.spider.strategy;

import us.codecraft.webmagic.Page;

/**
 * 页面处理策略总接口
 */
public interface PageProcessStrategy {
    /**
     * 页面处理接口
     *
     * @param page
     */
    void doProcess(Page page);
}
