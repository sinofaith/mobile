package cn.com.sinofaith.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtils {
	private static JedisPool pool = null;  
	static{
		//加载配置文件
		InputStream is = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
		Properties pro = new Properties();
		try {
			pro.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//获得池子对象
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(Integer.parseInt(pro.get("redis.maxIdle").toString()));//最大闲置个数
		poolConfig.setMinIdle(Integer.parseInt(pro.get("redis.minIdle").toString()));//最小闲置个数
		poolConfig.setMaxTotal(Integer.parseInt(pro.get("redis.maxTotal").toString()));//最大连接数
		pool =new JedisPool(poolConfig,pro.getProperty("redis.url"),Integer.parseInt(pro.get("redis.port").toString()));
	}
	public static Jedis getJedis(){
		return pool.getResource();
	}
	/**
	 * 回收Jedis对象资源
	 * @param jedis
	 */
	public static synchronized void returnResource(Jedis jedis){
		if(jedis != null){
			pool.returnResource(jedis);
		}
	}
}
