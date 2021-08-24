package com.nowcoder.spider.service.spider.strategy;


import com.nowcoder.spider.model.OriginBook;
import java.util.HashMap;
import java.util.Map;
import us.codecraft.webmagic.Page;

/**
 * OneBookProcessor Description: CreateDate: 2018/8/14
 *
 * @author nowcoder
 */

public class OneBookProcessor implements ProcessStrategy {

  /**
   * 当前解析缓存。
   */
  private Map<String, Object> cache = new HashMap<>(16);

  /**
   * 自动类型转换
   */
  private <T> T get(String key) {
    Object o = cache.get(key);
    if (o == null) {
      return null;
    }
    return (T) cache.get(key);
  }

  /**
   * 实现主要的爬取一本书的逻辑功能
   *
   * @param page 根据domain 已经爬取到的页面，可以看做一个html页面
   */
  @Override
  public void doProcess(Page page) {
    OriginBook originBook = new OriginBook();//封装结果
    cache.clear();

    originBook
        .setName(parseName(page))
        .setImg(parseImg(page))
        .setScore(parseScore(page));
    page.putField("book", originBook);
    System.out.println(originBook.getName() + " accomplish!");

  }

  protected String parseName(Page page) {
    return page.getHtml().xpath("//div[@id='wrapper']/h1/span/text()").toString();
  }

  protected String parseImg(Page page) {
    return page.getHtml().xpath("//div[@id='mainpic']/a/img/@src").toString();
  }

  protected int parseScore(Page page) {
    try {
      String score = page.getHtml().xpath(
          "//div[@id='interest_sectl']//strong[contains(@class, 'll') and contains(@class, 'rating_num')]/text()")
          .toString();
      score = score.replaceAll("[\\s|\\u00A0.]", "");
      return Integer.valueOf(score);
    } catch (Exception e) {
      return -1;
    }
  }
}
