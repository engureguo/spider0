package com.nowcoder.spider.service.spider.pipeline;

import com.nowcoder.spider.model.OriginBook;
import java.util.Vector;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

/**
 * OriginBookPipeline Description: CreateDate: 2018/8/15
 *
 * @author nowcoder
 */

/**
 * 关于Pipeline，一个PageProcessor被执行更新一遍所有的Pipeline 如果Page被skip掉了，则不执行Pipeline
 */
@Service
public class OriginBookPipeline implements CallablePipeline {

  private OriginBook originBook;

  public OriginBookPipeline() {
    obs = new Vector<>();
  }

  @Override
  public void process(ResultItems resultItems, Task task) {
    originBook = resultItems.get("book");
    originBook.setOriginUrl(task.getSite().getDomain());
    notifyObservers(getResult());
  }

  @Override
  public Object getResult() throws NullPointerException {

    if (!isQualifier()) {
      throw new NullPointerException("爬取结果不全");
    }

    return originBook;
  }

  /**
   * 该方法决定得到的结果是否合格，如果不合格抛出 {@link NullPointerException} 可以修改isQualifier判定方法 考虑到爬虫的不确定性，该条件尽可能的宽松
   */
  protected boolean isQualifier() {
    return StringUtils.isNoneEmpty(
//        originBook.getOriginUrl(),
//        originBook.getName(),
//        originBook.getAuthor(),
//        originBook.getPublisher(),
//        originBook.getISBN(),
//        originBook.getImg()
//        String.valueOf(originBook.getScore())
        "123"
    );
  }


  /*--  implements Observable  --*/
  private Vector<Observer> obs;

  @Override
  public synchronized void addObserver(Observer o) {
    if (o == null) {
      throw new NullPointerException();
    }
    if (!obs.contains(o)) {
      obs.addElement(o);
    }
  }

  @Override
  public synchronized void deleteObserver(Observer o) {
    obs.removeElement(o);
  }

  @Override
  public void notifyObservers(Object arg) {
    Object[] arrLocal;

    synchronized (this) {
      arrLocal = obs.toArray();
    }
    for (int i = arrLocal.length - 1; i >= 0; i--) {
      ((Observer) arrLocal[i]).update(this, arg);
    }
  }
}
