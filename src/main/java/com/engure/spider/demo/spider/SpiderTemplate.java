package com.engure.spider.demo.spider;

import com.engure.spider.demo.spider.pipeline.observable.ObserverablePipeline;
import com.engure.spider.demo.spider.pipeline.observer.ConsoleObserver;
import com.engure.spider.demo.spider.pipeline.observer.FileObserver;
import com.engure.spider.demo.spider.strategy.PageProcessStrategy;
import com.engure.spider.demo.spider.strategy.impl.IteratorPageProcessStrategy;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

@Service
public class SpiderTemplate implements PageProcessor {

    private static final int ONE_MINITE = 1000;
    private static final int HALF_MINITE = 500;

    private static final int TIME_OUT = ONE_MINITE * 3;    //下载器的超时时间
    private static final int SLEEP_TIME = HALF_MINITE;     //间隔时间
    private static final int RETRY_TIME = 3;               //下载失败的重试次数，默认0
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36 Edg/92.0.902.78";

    //配置爬虫
    private Site site = Site.me()
            .setTimeOut(TIME_OUT)
            .setSleepTime(SLEEP_TIME)
            .setRetryTimes(RETRY_TIME)
            .setUserAgent(USER_AGENT)
            //页面字符集
            .setCharset("UTF-8")
            //添加cookie
            .addCookie("test_cookie", "coooookie_value")
            //添加请求头
            .addHeader("test_header", "heeeeeeeeeeder_value");

    public Spider getSpider() {
        pageProcessStrategy = new IteratorPageProcessStrategy();
        return Spider.create(this);
    }

    public void startCrawl(String... urls) {

        ObserverablePipeline pipeline = new ObserverablePipeline();
        pipeline.addObserver(new ConsoleObserver());
        pipeline.addObserver(new FileObserver());

        //启动爬虫
        getSpider().addUrl(urls)
                //.addPipeline(new ConsolePipeline())
                .addPipeline(pipeline)
                .thread(5)
                .run();
    }

    private PageProcessStrategy pageProcessStrategy;

    /**
     * 传入迭代爬取策略
     *
     * @param pageProcessStrategy
     * @see IteratorPageProcessStrategy
     */
    public void setPageProcessStrategy(PageProcessStrategy pageProcessStrategy) {
        this.pageProcessStrategy = pageProcessStrategy;
    }

    @Override
    public void process(Page page) {

        if (pageProcessStrategy == null)
            throw new NullPointerException("页面爬取策略不能为空！");

        prePageProcess(page);
        pageProcessStrategy.doProcess(page);
        postPageProcess(page);
    }

    //添加两个额外方法，为类的拓展做准备，与策略模式无关
    //开闭原则

    //页面处理前置方法
    private void postPageProcess(Page page) {
    }

    //页面处理后置方法
    private void prePageProcess(Page page) {
    }

    @Override
    public Site getSite() {
        return site;
    }
}
