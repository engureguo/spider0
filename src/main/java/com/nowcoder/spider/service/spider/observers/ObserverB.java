package com.nowcoder.spider.service.spider.observers;

import com.nowcoder.spider.model.OriginBook;
import com.nowcoder.spider.service.spider.pipeline.Observable;
import com.nowcoder.spider.service.spider.pipeline.Observer;
import com.nowcoder.spider.service.spider.pipeline.OriginBookPipeline;

/**
 * ObserverB Description: CreateDate: 2018/8/23
 *
 * @author nowcoder
 */


public class ObserverB implements Observer {

  @Override
  public void update(Observable o, Object arg) {
    if (o instanceof OriginBookPipeline)
    //TODO with arg
      System.out.println("ObserverB " + ((OriginBook) arg).getName());
  }
}
