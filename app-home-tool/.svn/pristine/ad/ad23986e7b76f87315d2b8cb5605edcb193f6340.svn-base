package com.csk.tool.cache.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.csk.tool.cache.BaseCache;
import com.csk.tool.cache.ICache;

public class LocalCacheClient extends BaseCache implements ICache  {
	 // 缓存map
    private  Map<String, Object> cacheMap = new HashMap<String, Object>();
    // 缓存有效期map
    private  Map<String, Long> expireTimeMap = new HashMap<String, Long>();
 
 
    /**
     * 获取指定的value，如果key不存在或者已过期，则返回null
     * @param key
     * @return
     */
    public  Object get(String key) {
        if (!cacheMap.containsKey(key)) {
            return null;
        }
        if (expireTimeMap.containsKey(key)) {
            if (expireTimeMap.get(key) < System.currentTimeMillis()) { // 缓存失效，已过期
                return null;
            }
        }
        return cacheMap.get(key);
    }
  
    public  void set(String key, Object value) {
        cacheMap.put(key, value);
    }
  
    public  void set(final String key, Object value, long millSeconds) {
        final long expireTime = System.currentTimeMillis() + millSeconds;
        cacheMap.put(key, value);
        expireTimeMap.put(key, expireTime);
        if (cacheMap.size() > 2) { // 清除过期数据
            new Thread(new Runnable() {
                public void run() {
                    // 此处若使用foreach进行循环遍历，删除过期数据，会抛出java.util.ConcurrentModificationException异常
                    Iterator<Map.Entry<String, Object>> iterator = cacheMap.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, Object> entry = iterator.next();
                        if (expireTimeMap.containsKey(entry.getKey())) {
                            long expireTime = expireTimeMap.get(key);
                            if (System.currentTimeMillis() > expireTime) {
                                iterator.remove();
                                expireTimeMap.remove(entry.getKey());
                            }
                        }
                    }
                }
            }).start();
        }
    }
  
    public   boolean isExist(String key) {
        return cacheMap.containsKey(key);
    }

	@Override
	public void delete(String key) {
		 cacheMap.remove( key);
		 expireTimeMap.remove(key); 
	}
 
}
