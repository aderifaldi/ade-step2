package com.ade.step2.data.cache;

import com.ade.step2.model.local.Cache;

/**
 * Created by RadyaLabs PC on 12/12/2017.
 */

public class CacheManager {

    public static void storeCache(AppDatabase db, int cacheType, String data) {
        Cache cached = db.cacheDao().loadCacheById(cacheType);
        Cache cache = new Cache();

        if (cached == null){
            cache.id = cacheType;
            cache.json = "";
            db.cacheDao().insertCache(cache);
        }else {
            cache.id = cacheType;
            cache.json = data;
            db.cacheDao().updateCache(cache);
        }

    }

}
