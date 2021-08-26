package com.engure.spider.demo.spider.pipeline.observable;

import com.engure.spider.demo.Book0;
import com.engure.spider.demo.bean.Book1;
import com.engure.spider.demo.spider.pipeline.observer.Observer;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.Vector;

/*

自定义的 pipeline：
处理数据，将数据通知给不同的持久化方法

 */


public class ObserverablePipeline implements Observerable, Pipeline {

    private Vector<Observer> observers = new Vector<>(16);

    @Override
    public void notifyAllObservers(Object data) {
        for (Observer observer : observers) {
            observer.update(data);
        }
    }

    @Override
    public void addObserver(Observer observer) {
        if (!observers.contains(observer))
            observers.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        observers.removeElement(observer);
    }

    ////////////////////////////////////////////////////////////////////////

    @Override
    public void process(ResultItems resultItems, Task task) {
        //数据处理
        List<Book0> bookList = resultItems.get("data");
        String domain = task.getSite().getDomain();
        Book1 book1 = new Book1(bookList, domain);

        notifyAllObservers(book1);//写入bean
    }

}
