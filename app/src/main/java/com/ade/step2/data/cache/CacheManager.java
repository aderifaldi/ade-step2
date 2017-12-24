package com.ade.step2.data.cache;

import com.ade.step2.model.local.Cache;

/**
 * Created by RadyaLabs PC on 12/12/2017.
 */

public class CacheManager {

    public static void checkCache(AppDatabase db, int cacheType) {
        Cache cache = db.cacheDao().loadCacheById(cacheType);
        if (cache == null) {
            Cache data = new Cache();
            data.id = cacheType;
            data.json = "";
            db.cacheDao().insertCache(data);
        }
    }

    public static void storeCache(AppDatabase db, int cacheType, String data) {
        Cache cache = new Cache();
        cache.id = cacheType;
        cache.json = data;
        db.cacheDao().updateCache(cache);
    }

}
