package com.vindys.weatherly.data.model;

import com.vindys.weatherly.worker.ChildWorkerFactory;

import java.util.Map;
import java.util.Objects;

import javax.inject.Provider;

import androidx.work.ListenableWorker;

public class CollectionUtils {
    /**
     *
     * @param map workers
     * @param key workers name (class name)
     * @return
     */
    public static Provider<ChildWorkerFactory> getWorkerFactoryProviderByKey(Map<Class<? extends ListenableWorker>, Provider<ChildWorkerFactory>> map, String key) {
        for (Map.Entry<Class<? extends ListenableWorker>, Provider<ChildWorkerFactory>> entry : map.entrySet()) {
            if (Objects.equals(key, entry.getKey().getName())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
