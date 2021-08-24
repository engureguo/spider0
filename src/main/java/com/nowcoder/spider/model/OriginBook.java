package com.nowcoder.spider.model;

/**
 * OriginBook Description:从豆瓣上爬下来的原始信息，可能需要加工，参考 https://book.douban.com/subject/4010127/
 * CreateDate: 2018/8/14
 *
 * @author nowcoder
 */


public class OriginBook {

  private long id;

  /**
   * 数据来源网址
   */
  private String originUrl;
  private String name;

  /**
   * 封面图
   */
  private String img;

  /**
   * 豆瓣得分 * 10 数据库存整形。
   */
  private int score;


  //--- properties --//
  public long getId() {
    return id;
  }

  public OriginBook setId(long id) {
    this.id = id;
    return this;
  }

  public String getOriginUrl() {
    return originUrl;
  }

  public OriginBook setOriginUrl(String originUrl) {
    this.originUrl = originUrl;
    return this;
  }

  public String getName() {
    return name;
  }

  public OriginBook setName(String name) {
    this.name = name;
    return this;
  }

  public String getImg() {
    return img;
  }

  public OriginBook setImg(String img) {
    this.img = img;
    return this;
  }

  public int getScore() {
    return score;
  }

  public OriginBook setScore(int score) {
    this.score = score;
    return this;
  }
}
