package com.codeliu.course_select_system.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Set;

/**
 * @ClassName liuxiaoping
 * @Description jedis工具类
 * @Author liu
 * @Date 2018/12/27 19:25
 * @Version 1.0
 */
@Service
public class JedisAdapter implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(JedisAdapter.class);
    private JedisPool pool = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        // pool = new JedisPool("127.0.0.1", 6379);
    }

    public JedisAdapter() {
        pool = new JedisPool("localhost", 6379);
    }

    /**
     * get
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
            // redis连接池要及时的关闭，否则连接池的redis会被用光
            if (jedis != null) {
                jedis.close();
            }
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * set
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * sadd(key, value)往集合中加入一个key-value
     *
     * @param key
     * @param value
     * @return
     */
    public long sadd(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            // 返回多少个值
            return jedis.sadd(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
            // redis连接池要及时的关闭，否则连接池的redis会被用光
            if (jedis != null) {
                jedis.close();
            }
            return 0;
        } finally {
            // redis连接池要及时的关闭，否则连接池的redis会被用光
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * srem(key, value)从这个集合中移除这个key-value
     *
     * @param key
     * @param value
     * @return
     */
    public long srem(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.srem(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
            // redis连接池要及时的关闭，否则连接池的redis会被用光
            if (jedis != null) {
                jedis.close();
            }
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * sismember(key, value)判断集合中是否有这个key-value
     *
     * @param key
     * @param value
     * @return
     */
    public boolean sismember(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sismember(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
            // redis连接池要及时的关闭，否则连接池的redis会被用光
            if (jedis != null) {
                jedis.close();
            }
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * scard(key)获取集合成员数量
     *
     * @param key
     * @return
     */
    public long scard(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.scard(key);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
            // redis连接池要及时的关闭，否则连接池的redis会被用光
            if (jedis != null) {
                jedis.close();
            }
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void setex(String key, String value) {
        // 验证码, 防机器注册，记录上次注册时间，有效期3天
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.setex(key, 10, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long lpush(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.lpush(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
            // redis连接池要及时的关闭，否则连接池的redis会被用光
            if (jedis != null) {
                jedis.close();
            }
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public List<String> brpop(int timeout, String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.brpop(timeout, key);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
            // redis连接池要及时的关闭，否则连接池的redis会被用光
            if (jedis != null) {
                jedis.close();
            }
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 保存一个对象，相当于缓存到Redis中
     *
     * @param key
     * @param obj
     */
    public void setObject(String key, Object obj) {
        // 保存序列化为JSON串
        set(key, JSON.toJSONString(obj));
    }

    /**
     * 获取一个对象
     *
     * @param key
     * @param clazz
     * @return
     */
    public <T> T getObject(String key, Class<T> clazz) {
        String value = get(key);
        if (value != null) {
            return JSON.parseObject(value, clazz);
        }
        return null;
    }

    /**
     * 往有序集合增加一个成员
     * @param key
     * @param courseId
     * @param evaluation
     * @return
     */
    public long zadd(String key, Float evaluation, String courseId) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            // 返回多少个值
            return jedis.zadd(key, evaluation, courseId);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
            // redis连接池要及时的关闭，否则连接池的redis会被用光
            if (jedis != null) {
                jedis.close();
            }
            return 0;
        } finally {
            // redis连接池要及时的关闭，否则连接池的redis会被用光
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 返回有序集合中，指定区间的元素,按递减的顺序
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrevrange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            // 返回多少个值
            return jedis.zrevrange(key, start, end);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
            // redis连接池要及时的关闭，否则连接池的redis会被用光
            if (jedis != null) {
                jedis.close();
            }
            return null;
        } finally {
            // redis连接池要及时的关闭，否则连接池的redis会被用光
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public boolean delete(String userId) {
        Jedis jedis = null;
        try {
            logger.warn("jedis1 = " + jedis);
            jedis = pool.getResource();
            logger.warn("jedis2 = " + jedis);
            if(jedis.exists(userId)) {
                jedis.del(userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error("发生异常" + e.getMessage());
            // redis连接池要及时的关闭，否则连接池的redis会被用光
            if (jedis != null) {
                jedis.close();
            }
            return false;
        } finally {
            // redis连接池要及时的关闭，否则连接池的redis会被用光
            if (jedis != null) {
                jedis.close();
            }
        }
        return true;
    }

}
