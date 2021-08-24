package com.nowcoder.spider.service.spider.strategy;

import us.codecraft.webmagic.Page;

/**
 * FilterProcessor Description: CreateDate: 2018/8/15
 *
 * @author nowcoder
 */

/**
 * FilterProcessor 是ProcessStrategy的装饰类，用来扩展ProcessStrategy的爬虫方法。
 * 这是一个伪实现类，真正的装饰类需要继承这个类。
 * 设计参考 {@link java.io.FilterInputStream}
 */
public class FilterProcessor implements ProcessStrategy {

  protected volatile ProcessStrategy processStrategy;

  public FilterProcessor(ProcessStrategy processStrategy){
    this.processStrategy = processStrategy;
  }

  @Override
  public void doProcess(Page page) {
    processStrategy.doProcess(page);
  }
}
