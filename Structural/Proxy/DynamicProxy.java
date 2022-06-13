package Structural.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

interface Human {
    void talk();
    void walk();
}


class Person implements Human
{
    @Override
    public void walk()
    {
        System.out.println("I am walking");
    }

    @Override
    public void talk()
    {
        System.out.println("I am talking");
    }
}
class LoggingHandler implements InvocationHandler {

    private final Object target;
    private Map<String, Integer> calls;

    LoggingHandler(Object target) {
        this.target = target;
        calls = new HashMap<>();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //Before each method invocation this function runs.
        String name = method.getName();
        if(name.contains("toString")) {
            return calls.toString();
        }
        // add or increment number of calls
        calls.merge(name, 1, Integer::sum);
        return method.invoke(target, args);
    }
}

public class DynamicProxy {
    /*Dynamic proxies are created at runtime*/
    @SuppressWarnings("unchecked")
    public static <T> T withLogging(T target, Class<T> itself) {
        return (T) Proxy.newProxyInstance(
                itself.getClassLoader(),
                new Class<?>[] {itself},
                new LoggingHandler(target));

    }

    public static void main(String[] args) {
        Person person = new Person();
        Human logged = withLogging(person, Human.class);
        logged.walk();
        logged.talk();
        logged.talk();

        System.out.println(logged);
    }
}
