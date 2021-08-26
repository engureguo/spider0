package com.engure.spider.demo.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * pipeline做麻子
 */

/**
 * 思考：
 * 1、怎么理解数据处理？对爬取的数据进行 加工、检错、包装等，将最终的数据持久化到文件、数据库
 * 2、如果让我来写用于数据处理的PipeLine该怎么写？
 * 直接继承PipeLine接口？这样有什么问题
 */

public class T {
}

/*

装饰器模式：适合多步处理，一层一层递进类型的
pipeline数据处理：相同的数据处理，不同的持久化方式

 */


//伪实现类
class MyPipeLine0Decorator implements Pipeline {

    Pipeline pipeline;

    public MyPipeLine0Decorator(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        if (pipeline == null) throw new NullPointerException("pipeline = null!");

        pipeline.process(resultItems, task);
    }
}

//装饰器，装饰 MyPipeLine0Decorator，为其增加功能
class Pipeline1 extends MyPipeLine0Decorator {

    public Pipeline1() {
        super(new MyPipeLine());//默认的pipeline
    }

    public Pipeline1(Pipeline pipeline) {
        super(pipeline);//指定具体的pipeline
    }

    @Override
    public void process(ResultItems resultItems, Task task) {

        // 1.处理

        // 2.如果满足某个条件，就交给 装饰器使用传递的 pipeline 来处理
        if (2 > 1) {
            super.process(resultItems, task);
        }

    }
}

//对pipeline1进一步拓展：实现pipeline1的伪实现类
class Pipeline1Decorator extends Pipeline1 {

    private Pipeline1 pipeline1;

    public Pipeline1Decorator(Pipeline1 pipeline1) {
        this.pipeline1 = pipeline1;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        pipeline1.process(resultItems, task);
    }
}

//装饰器，装饰pipeline1的为实现类
class Pipeline2 extends Pipeline1Decorator {

    public Pipeline2() {
        super(new Pipelinex());//默认为一个Pipeline1的实现类
    }

    public Pipeline2(Pipeline1 pipeline1) {
        super(pipeline1);//指定一个Pipeline1的实现类
    }

    @Override
    public void process(ResultItems resultItems, Task task) {

        // 1.处理

        // 2.如果满足某个条件，就交给 pipeline1 处理（默认）
        if (2 > 1) {
            super.process(resultItems, task);
        }
    }
}


class Pipelinex extends Pipeline1 {

}



