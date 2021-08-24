package com.nowcoder.spider;

import com.nowcoder.spider.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpiderApplication implements CommandLineRunner {

  @Autowired
  private SpiderService spiderService;

	public static void main(String[] args) {
		SpringApplication.run(SpiderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
    	spiderService.getLotsOfBooks("https://book.douban.com/tag/%E7%A7%91%E6%99%AE");
	}
}
