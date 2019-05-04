package com.codeliu.course_select_system.util;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 推荐算法
 */
public class RecommendUtils {

    private static Logger logger = LoggerFactory.getLogger(RecommendUtils.class);

    private static JedisAdapter jedisAdapter = new JedisAdapter();

    /**
     * 读取数据库的评分数据生成dat文件
     * @param userId
     * @param evaluationList
     * @return
     */
    public static boolean generatDatFile(String userId, List<Map<String, Object>> evaluationList) {

        try {
            String url = "E:\\data\\" + userId + "\\";
            File file = new File(url);
            file.mkdirs();

            File f = new File(url + "info.dat");
            // 生成dat文件
            f.createNewFile();

            FileWriter fw = new FileWriter(url + "info.dat");

            if(evaluationList != null && evaluationList.size() > 0) {
                for (int i = 0; i < evaluationList.size(); i++) {
                    Map<String, Object> map = evaluationList.get(i);
                    // 写入文件
                    // 学号把B去掉，因为推荐算法只能传入数字类型的
                    fw.write(map.get("student_id").toString().substring(1) + ",");
                    fw.write(map.get("course_id").toString() + ",");
                    fw.write(map.get("rank").toString() + "\n");
                    fw.flush();
                }
            }

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 基于用户的推荐
     * 解析dat文件并生成推荐课程id和匹配度
     * 存入redis
     * @return
     */
    public static boolean BaseUserRecommender(String userId) {

        try {
            File file = new File("E:\\data\\" + userId + "\\info.dat");
            long id = Long.parseLong(userId.substring(1));

            // 建立推荐模型
            DataModel dataModel = new FileDataModel(file);
            // 使用皮尔逊
            UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
            //计算最近邻域，邻居有两种算法，基于固定数量的邻居和基于相似度的邻居，这里使用基于固定数量的邻居
            UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(2, similarity, dataModel);

            //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于用户的协同过滤推荐
            Recommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, similarity);
            //给用户ID推荐15门课程
            List<RecommendedItem> recommendedItemList = recommender.recommend(id, 15);
            logger.warn("recommendedItemList = " + recommendedItemList);

            // 清空原来的
            jedisAdapter.delete(userId);

            // 将结果存入redis
            for (RecommendedItem recommendedItem : recommendedItemList) {
                logger.info("recommendedItem = " + recommendedItem);
                jedisAdapter.zadd(userId, recommendedItem.getValue(), String.valueOf(recommendedItem.getItemID()));
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 基于物品的推荐
     * 解析dat文件并生成推荐课程id和匹配度
     * 存入redis
     * @return
     */
    public static boolean BaseItemRecommender(String userId) {

        try {
            File file = new File("E:\\data\\" + userId + "\\info.dat");
            long id = Long.parseLong(userId.substring(1));
            logger.info("id = " + id);

            // 建立推荐模型
            DataModel dataModel = new FileDataModel(file);
            //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
            ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(dataModel);
            //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于物品的协同过滤推荐
            Recommender recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
            //给用户ID推荐15门课程
            List<RecommendedItem> recommendedItemList = recommender.recommend(id, 15);
            logger.warn("recommendedItemList = " + recommendedItemList);

            // 清空原来的
            jedisAdapter.delete(userId);

            // 将结果存入redis
            for (RecommendedItem recommendedItem : recommendedItemList) {
                logger.info("recommendedItem = " + recommendedItem);
                jedisAdapter.zadd(userId, recommendedItem.getValue(), String.valueOf(recommendedItem.getItemID()));
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    public static void main(String[] args) {
//        try {
//            //准备数据 这里是电影评分数据
//            File file = new File("E:\\data\\ml-1m\\ratings.dat");
//            //将数据加载到内存中，GroupLensDataModel是针对开放电影评论数据的
//            DataModel dataModel = new GroupLensDataModel(file);
//            //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
//            UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
//            //计算最近邻域，邻居有两种算法，基于固定数量的邻居和基于相似度的邻居，这里使用基于固定数量的邻居
//            UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(100, similarity, dataModel);
//            //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于用户的协同过滤推荐
//            Recommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, similarity);
//            //给用户ID等于5的用户推荐10部电影
//            List<RecommendedItem> recommendedItemList = recommender.recommend(5, 10);
//            //打印推荐的结果
//            System.out.println("使用基于用户的协同过滤算法");
//            System.out.println("为用户5推荐10个商品");
//            for (RecommendedItem recommendedItem : recommendedItemList) {
//                System.out.println(recommendedItem);
//            }
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
        //BaseItemRecommender("B15041201");
        BaseUserRecommender("B15041201");
    }

}
