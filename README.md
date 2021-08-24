## 学生选课推荐系统

### 0、体验账号

管理员：admin  123456

教师：JS1001  123456

学生：B15041212  123456

### 1、使用的技术
后端：SpringBoot + MyBatis + Shiro

前端：BootStrap 

模板引擎：Thymeleaf 

数据库：MySQL 

项目构建构建：Maven

推荐算法：协同过滤推荐算法。基于学生的成绩、对课程的评分以及课程之间的关联等因素，再通过一定的计算公式实现推荐。

### 2、角色分类
![登录页面](https://github.com/stronglxp/courseSelectRecommendSystem/blob/master/src/main/resources/static/images/%E7%99%BB%E5%BD%95.png)
##### 2.1 管理员
![学生管理](https://github.com/stronglxp/courseSelectRecommendSystem/blob/master/src/main/resources/static/images/%E5%AD%A6%E7%94%9F%E7%AE%A1%E7%90%86.png)
![教师管理](https://github.com/stronglxp/courseSelectRecommendSystem/blob/master/src/main/resources/static/images/%E6%95%99%E5%B8%88%E7%AE%A1%E7%90%86.png)
![课程管理](https://github.com/stronglxp/courseSelectRecommendSystem/blob/master/src/main/resources/static/images/%E8%AF%BE%E7%A8%8B%E7%AE%A1%E7%90%86.png)
![院系管理](https://github.com/stronglxp/courseSelectRecommendSystem/blob/master/src/main/resources/static/images/%E9%99%A2%E7%B3%BB%E7%AE%A1%E7%90%86.png)
![密码重置](https://github.com/stronglxp/courseSelectRecommendSystem/blob/master/src/main/resources/static/images/%E9%87%8D%E7%BD%AE.png)

##### 2.2 教师
![教师](https://github.com/stronglxp/courseSelectRecommendSystem/blob/master/src/main/resources/static/images/%E6%95%99%E5%B8%88.png)
![打分](https://github.com/stronglxp/courseSelectRecommendSystem/blob/master/src/main/resources/static/images/%E6%89%93%E5%88%86.png)

##### 2.3 学生
![学生](https://github.com/stronglxp/courseSelectRecommendSystem/blob/master/src/main/resources/static/images/%E5%AD%A6%E7%94%9F.png)
![按人数推荐](https://github.com/stronglxp/courseSelectRecommendSystem/blob/master/src/main/resources/static/images/%E6%8C%89%E4%BA%BA%E6%95%B0.png)
![综合推荐](https://github.com/stronglxp/courseSelectRecommendSystem/blob/master/src/main/resources/static/images/%E7%BB%BC%E5%90%88%E6%8E%A8%E8%8D%90.png)

### 3、写在后面

这是好几年前写的代码了，如果有很挫的地方，大家不要介意啦。

#### 另外可以关注我的公众号【秃头哥编程】，佛系更新。

<img src="https://i.ibb.co/5K4Ty8B/image.webp">