package com.engure.spider.demo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * webmagic 官方示例程序
 */
public class GithubRepoPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        //添加新网址 github.com/username/reponame
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());

        //提取页面数据
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
        if (page.getResultItems().get("name") == null) {
            //pipeline不处理这个页面
            page.setSkip(true);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor())
                .addPipeline(new ConsolePipeline())
                .addUrl("https://github.com/code4craft").thread(5).run();
    }
}
