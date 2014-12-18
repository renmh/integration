package com.pansky.integration.common.test;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class TesteCache {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        // 指定ehcache.xml的位置
        String fileName = "D:/workspace_integration/integration/src/main/resources/cache/ehcache-hibernate-local.xml";
        CacheManager manager = new CacheManager(fileName);
        // 取出所有的cacheName
        String names[] = manager.getCacheNames();
        for (int i = 0; i < names.length; i++) {
            Cache cache = manager.getCache(names[i]);
            System.out.println(names[i]+":="+cache.getSize());
        }
    
        Cache cache = manager.getCache("com.thinkgem.jeesite.modules.sys.entity.Office");
        System.out.println(cache.getSize());
        
        
        for(int i=0;i<100000;i++){
            cache.put(new Element("key1"+ i , "values1"+i));
        }
        
        
//        cache.flush();
        
        manager.shutdown();
    }
}
