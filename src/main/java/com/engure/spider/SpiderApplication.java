package com.engure.spider;

import com.engure.spider.demo.spider.SpiderTemplate;
import com.engure.spider.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpiderApplication implements CommandLineRunner {

    @Autowired
    private SpiderService spiderService;

    @Autowired
    private SpiderTemplate spiderTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpiderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //spiderService.getLotsOfBooks("https://book.douban.com/tag/%E7%A7%91%E6%99%AE");
        spiderTemplate.startCrawl(urlArray());
    }

    private static String[] urlArray() {
        int PAGE_NUM = 5;
        List<String> urlList = new ArrayList<>();

        // 图书页
        // url-pattern https://book.douban.com/tag/%E4%BA%92%E8%81%94%E7%BD%91?start={20 * i}&type=T
        for (int i = 0; i < PAGE_NUM; i++) {
            String url = "https://book.douban.com/tag/%E4%BA%92%E8%81%94%E7%BD%91?" +
                    "start=" + 20 * i + "&type=T";
            urlList.add(url);
        }

        return toUrlArray(urlList);
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
