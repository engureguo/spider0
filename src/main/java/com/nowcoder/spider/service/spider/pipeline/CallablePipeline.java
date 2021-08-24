package com.nowcoder.spider.service.spider.pipeline;

import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * CallablePipeline Description: CreateDate: 2018/8/15
 *
 * @author nowcoder
 */

/**
 * Pipeline一般直接输出到文件、数据库、控制台比较方便。但是输出到一个对象没有返回结果。 此接口扩展自 {@link Pipeline}，用来获取一个结果
 */
public interface CallablePipeline extends Pipeline,Observable {

  Object getResult() throws NullPointerException;
}
