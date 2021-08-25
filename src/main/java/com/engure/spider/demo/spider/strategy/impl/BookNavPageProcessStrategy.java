package com.engure.spider.demo.spider.strategy.impl;

import com.engure.spider.demo.spider.strategy.PageProcessStrategy;
import com.engure.spider.demo.Book0;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

public class BookNavPageProcessStrategy implements PageProcessStrategy {

    /**
     * 提取图书信息导航页信息，比如 https://book.douban.com/tag/互连网?start=40&type=T
     *
     * @param page
     */
    @Override
    public void doProcess(Page page) {

        // 图书标签页
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
