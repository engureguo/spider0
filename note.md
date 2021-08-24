## webmagic

参考：[webmagic的使用学习 - 会飞的大野鸡 - 博客园 (cnblogs.com)](https://www.cnblogs.com/B-rabbit/p/13781986.html)

### 概述

webmagic是一个开源的Java垂直爬虫框架，目标是简化爬虫的开发流程，让开发者专注于逻辑功能的开发
WebMagic项目代码分为核心和扩展两部分：

- 核心部分(webmagic-core)是一个精简的、模块化的爬虫实现，而扩展部分则包括一些便利的、实用性的功能。WebMagic的架构设计参照了Scrapy，目标是尽量的模块化，并体现爬虫的功能特点。这部分提供非常简单、灵活的API，在基本不改变开发模式的情况下，编写一个爬虫
- 扩展部分(webmagic-extension)提供一些便捷的功能，例如注解模式编写爬虫等。同时内置了一些常用的组件，便于爬虫开发

### 设计原理

WebMagic 的结构分为 Downloader、PageProcessor、Scheduler、Pipeline 四大组件，并由 Spider 将它们彼此组织起来。
WebMagic 总体架构图如下：

<img src="images/note.assets/o_200615125739webmagic.png" alt="技术" style="zoom:80%;" />

### WebMagic的四个组件

- Downloader：**Downloader负责从互联网上下载页面，以便后续处理**。WebMagic默认使用了Apache HttpClient作为下载工具。
- PageProcessor：**PageProcessor负责解析页面，抽取有用信息，以及发现新的链接**。WebMagic使用Jsoup作为HTML解析工具，并基于其开发了解析XPath的工具Xsoup。在这四个组件中，PageProcessor对于每个站点每个页面都不一样，是需要使用者定制的部分。
- Scheduler：Scheduler**负责管理待抓取的URL，以及一些去重的工作**。WebMagic默认提供了JDK的内存队列来管理URL，并用集合来进行去重。也支持使用Redis进行分布式管理。除非项目有一些特殊的分布式需求，否则无需自己定制Scheduler。
- Pipeline：Pipeline**负责抽取结果的处理，包括计算、持久化到文件、数据库等**。WebMagic默认提供了“输出到控制台”和“保存到文件”两种结果处理方案。Pipeline定义了结果保存的方式，如果你要保存到指定数据库，则需要编写对应的Pipeline。对于一类需求一般只需编写一个Pipeline。

### 用于数据流转的对象

- Request：Request是对URL地址的一层封装，一个Request对应一个URL地址。它是PageProcessor与Downloader交互的载体，也是PageProcessor控制Downloader唯一方式。除了URL本身外，它还包含一个Key-Value结构的字段extra。你可以在extra中保存一些特殊的属性，然后在其他地方读取，以完成不同的功能。例如附加上一个页面的一些信息等。
- Page：Page代表了从Downloader下载到的一个页面——可能是HTML，也可能是 **JSON** 或者其他文本格式的内容。Page是WebMagic抽取过程的核心对象，它提供一些方法可供抽取、结果保存等。
- **ResultItems**：ResultItems 相当于一个Map，它**保存PageProcessor处理的结果，供Pipeline使用**。它的API与Map很类似，值得注意的是它有一个字段skip，若设置为true，则不应被Pipeline处理。

