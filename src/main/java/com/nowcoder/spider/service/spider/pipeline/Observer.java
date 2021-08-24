package com.nowcoder.spider.service.spider.pipeline;

/**
 * Observer Description: CreateDate: 2018/8/23
 *
 * @author nowcoder
 */

/**
 * 参考{@link java.util.Observer}设计
 */
public interface Observer {

  void update(Observable o, Object arg);

}
