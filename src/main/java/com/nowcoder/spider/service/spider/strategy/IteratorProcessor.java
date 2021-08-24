package com.nowcoder.spider.service.spider.strategy;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;

/**
 * IteratorProcessor Description: CreateDate: 2018/8/15
 *
 * @author nowcoder
 */

/**
 * 装饰者，用来装饰book的迭代爬取功能
 */
public class IteratorProcessor extends FilterProcessor {

  private Set<String> requestCache = new HashSet<>(32);

  public IteratorProcessor() {
    super(new OneBookProcessor());
  }

  public IteratorProcessor(ProcessStrategy processStrategy) {
    super(processStrategy);
  }

  @Override
  public void doProcess(Page page) {
    if (StringUtils.startsWith(page.getRequest().getUrl(), "https://book.douban.com/tag/")) {
      requestCache.addAll(page.getHtml().regex("https://book.douban.com/subject/[0-9]+/?").all());
      page.addTargetRequests(new ArrayList<>(requestCache));
      requestCache.clear();
      page.setSkip(true);
    } else {
      processStrategy.doProcess(page);
    }
  }
}
