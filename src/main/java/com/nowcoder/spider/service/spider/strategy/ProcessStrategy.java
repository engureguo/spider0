package com.nowcoder.spider.service.spider.strategy;

import us.codecraft.webmagic.Page;

/**
 * 策略模式总接口
 *
 * @author nowcoder
 */


public interface ProcessStrategy {

    /**
     * 爬虫具体执行的方法 {@link Page}
     */
    void doProcess(Page page);

}
