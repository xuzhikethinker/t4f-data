package io.aos.cache.guava;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.Sets;

public class GuavaTest {

    private Map<String, Set<String>> pingsToEmit;

    private LoadingCache<String, Set<String>> expiringStringsPerVisitor;

    @Test
    public void test() throws InterruptedException {

        pingsToEmit = new ConcurrentHashMap<String, Set<String>>();

        expiringStringsPerVisitor = CacheBuilder.newBuilder() //
                .maximumSize(10) //
                .expireAfterWrite(1, TimeUnit.SECONDS) //
                .removalListener(new RemovalListener<String, Set<String>>() {

                    @Override
                    public void onRemoval(RemovalNotification<String, Set<String>> removalNotification) {

                        System.out.println("Removed key: " + removalNotification.getKey());

                    }
                }).build(new CacheLoader<String, Set<String>>() {

                    public Set<String> load(String visitorId) {

                        return Collections.synchronizedSet(new HashSet<String>());
                    }
                });

        String[] keys = new String[] { "visitorId1", "visitorId2", "visitorId3" };

        Set<String> value0 = Sets.newHashSet("value0");
        expiringStringsPerVisitor.put(keys[0], value0);
        Set<String> pingSet0 = null;
        try {
            pingSet0 = expiringStringsPerVisitor.get(keys[0]);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(keys[0] + ": " + pingSet0);

        Set<String> pingSet1 = null;
        try {
            pingSet1 = expiringStringsPerVisitor.get(keys[1], new Callable<Set<String>>() {

                @Override
                public Set<String> call() throws Exception {

                    return Collections.synchronizedSet(new HashSet<String>());
                }
            });
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(keys[1] + ": " + pingSet1);

        Set<String> pingSet2 = null;
        try {
            pingSet2 = expiringStringsPerVisitor.get(keys[2], new Callable<Set<String>>() {

                @Override
                public Set<String> call() throws Exception {

                    return Collections.synchronizedSet(new HashSet<String>());
                }
            });
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(keys[2] + ": " + pingSet2);

        while (expiringStringsPerVisitor.asMap().size() > 0) {
            TimeUnit.SECONDS.sleep(1);
            expiringStringsPerVisitor.cleanUp();
        }

    }

}
