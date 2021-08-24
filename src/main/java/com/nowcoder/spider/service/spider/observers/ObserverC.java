package com.nowcoder.spider.service.spider.observers;

import com.nowcoder.spider.model.OriginBook;
import com.nowcoder.spider.service.spider.pipeline.Observable;
import com.nowcoder.spider.service.spider.pipeline.Observer;
import com.nowcoder.spider.service.spider.pipeline.OriginBookPipeline;

public class ObserverC implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof OriginBookPipeline)
            System.out.println("Observer C " + ((OriginBook) arg).getOriginUrl());
    }
}
