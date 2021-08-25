package com.engure.spider.service.spider.observers;

import com.engure.spider.model.OriginBook;
import com.engure.spider.service.spider.pipeline.OriginBookPipeline;
import com.engure.spider.service.spider.pipeline.Observable;
import com.engure.spider.service.spider.pipeline.Observer;

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
