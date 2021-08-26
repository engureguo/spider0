package com.engure.spider.demo.spider.pipeline.observable;

import com.engure.spider.demo.spider.pipeline.observer.Observer;

public interface Observerable {
    void notifyAllObservers(Object data);

    void addObserver(Observer observer);

    void deleteObserver(Observer observer);
}
