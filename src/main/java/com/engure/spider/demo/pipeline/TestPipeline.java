package com.engure.spider.demo.pipeline;

import com.engure.spider.demo.Book0;
import com.engure.spider.demo.FirstSpider;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

public class TestPipeline implements PageProcessor {

    //抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(500).setTimeOut(1000 * 5);

    @Override
    public void process(Page page) {

        // 从page中提取信息，判断page类型，有以下几种操作
        //      1. 向队列中加入新的网页  page.addTargetRequests(page.getHtml().links().regex("(https://book.douban.com/subject/\\w+)").all());
        //      2. 解析page数据，将数据放入，以供pipeline使用 page.putField("data", bookList);
        //      3. 不使用 pipeline 处理数据，page.setSkip(true)

        String item = page.getHtml().xpath("//*[@id=\"content\"]/h1/text()").toString();

        if (item != null) {

            // 提取图书页，加入队列
            // page.addTargetRequests(page.getHtml().links().regex("(https://book.douban.com/subject/\\w+)").all());

            System.out.println("-----------------------------------------------------");

            List<String> titles = page.getHtml().xpath("//*[@id=\"subject_list\"]/ul/li/div[2]/h2/a/@title").all();
            List<String> details = page.getHtml().xpath("//*[@id=\"subject_list\"]/ul/li/div[2]/div[1]/text()").all();
            List<String> desps = page.getHtml().xpath("//*[@id=\"subject_list\"]/ul/li/div[2]/p/text()").all();
            List<String> hrefs = page.getHtml().xpath("//*[@id=\"subject_list\"]/ul/li/div[2]/h2/a/@href").all();

            List<Book0> bookList = new ArrayList<>();
            try {
                for (int i = 0; i < titles.size(); i++) {
                    //写入每本书都都有的信息
                    bookList.add(new Book0(titles.get(i), details.get(i), desps.get(i), hrefs.get(i)));
                    //留一手，避免数据错乱，数据越界
                }
                page.putField("data", bookList);
            } catch (Exception e) {
                page.setSkip(true);
                throw new RuntimeException("信息数量不一致，拒绝写入！");
            }

        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        int PAGE_NUM = 5;
        List<String> urlList = new ArrayList<>();

        // 图书页
        // url-pattern https://book.douban.com/tag/%E4%BA%92%E8%81%94%E7%BD%91?start={20 * i}&type=T
        for (int i = 0; i < PAGE_NUM; i++) {
            String url = "https://book.douban.com/tag/%E4%BA%92%E8%81%94%E7%BD%91?" +
                    "start=" + 20 * i + "&type=T";
            urlList.add(url);
        }

        Spider.create(new FirstSpider())
                .addUrl(toUrlArray(urlList))
                //开启5个线程抓取
                .thread(1)
                //存放为json文件，项目根路径下
//                .addPipeline(new JsonFilePipeline(FirstSpider.class.getResource("F:/webmagic/data").getPath()))
                .addPipeline(new MyPipeLine())
                //启动爬虫
                .run();
    }

    private static String[] toUrlArray(List<String> urlList) {
        if (null == urlList) throw new RuntimeException("list is null!");
        String[] ss = new String[urlList.size()];
        for (int i = 0; i < urlList.size(); i++) {
            ss[i] = urlList.get(i);
        }
        return ss;
    }
}

/**
 * PipeLine 是什么
 */

class MyPipeLine implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("----------------------------------");
        System.out.println("allAttr = " + resultItems.getAll());
        Site site = task.getSite();
        System.out.println("uuid = " + task.getUUID());
        System.out.println("domain = " + site.getDomain());
        System.out.println("cookies = " + site.getAllCookies());
        System.out.println("headers = " + site.getHeaders());
        System.out.println("==================================");
    }
}


