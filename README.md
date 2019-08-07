# Elasticsearch基于SpringBoot的Demo

## 1.安装Elasticsearch
https://www.elastic.co/cn/downloads/elasticsearch
## 2.安装中文IK分词插件
https://github.com/medcl/elasticsearch-analysis-ik
下载版本对应的ik分词器，放置在plugin目录下即可

## 3.安装elasticsearch-head
elasticsearch-head将是一款专门针对于elasticsearch的客户端工具
github地址如下
https://github.com/mobz/elasticsearch-head
安装好在根目录执行以下两步操作（依赖node.js，需要安装node.js环境）

```
npm install
npm run start
```

在Elasticsearch的config下elasticsearch.yml中加入以下配置支持跨域访问

```
http.cors.enabled: true
http.cors.allow-origin: "*"
```


elasticsearch都使用默认配置即可

