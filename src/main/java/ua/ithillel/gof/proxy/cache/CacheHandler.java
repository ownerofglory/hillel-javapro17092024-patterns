package ua.ithillel.gof.proxy.cache;

import ua.ithillel.gof.proxy.anno.Cacheable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.WeakHashMap;

public class CacheHandler implements InvocationHandler {
    private final WeakHashMap<Object, Object> cache = new WeakHashMap<>();
    private final Object target;

    public CacheHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method: " + method.getName());
        System.out.println("args: " + Arrays.toString(args));

        if (args.length > 0 && method.isAnnotationPresent(Cacheable.class)) {
            Object firstArg = args[0];

            if (cache.containsKey(firstArg)) {
                return cache.get(firstArg);
            };

            Object result = method.invoke(target, args);
            System.out.println("method: " + method.getName() + "returned: " + result);

            cache.put(firstArg, result);

            return result;
        }



// default behavior
        Object result = method.invoke(proxy, args);
        return result;
    }
}
