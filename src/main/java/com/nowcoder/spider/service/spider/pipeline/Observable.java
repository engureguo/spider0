package com.nowcoder.spider.service.spider.pipeline;

/**
 * Observable Description: CreateDate: 2018/8/23
 *
 * @author nowcoder
 */


/**
 * java.util.Observable 不是一个好的设计。如果需要拥有Observable功能而去
 * 继承一个类的话，代价太沉重。我重新设计的接口，留给具体的类去实现。
 */
public interface Observable {

  void addObserver(Observer o);

  void deleteObserver(Observer o);

  void notifyObservers(Object arg);
}
