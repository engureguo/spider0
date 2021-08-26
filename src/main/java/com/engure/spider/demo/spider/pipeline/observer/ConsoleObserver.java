package com.engure.spider.demo.spider.pipeline.observer;

/**
 * 观察者，将数据写入console
 */

public class ConsoleObserver implements Observer {

    @Override
    public void update(Object data) {
        if (data == null)
            throw new NullPointerException("data is null!");

        System.out.println("console output -----------------------------");
        System.out.println(data);
        System.out.println("console output over ------------------------");
    }
}
