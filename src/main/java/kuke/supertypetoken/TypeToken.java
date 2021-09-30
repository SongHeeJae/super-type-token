package kuke.supertypetoken;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeToken {

    static class TypesafeMap {
        Map<Class<?>, Object> map = new HashMap<>();

        <T> void put(Class<T> clazz, T value) {
            map.put(clazz, value);
        }

        <T> T get(Class<T> clazz) {
            return clazz.cast(map.get(clazz));
        }
    }

    // type token : 타입 정보를 값으로 해서 넘기겠다는 것
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        final TypesafeMap m = new TypesafeMap();
        m.put(String.class, "Value");
        m.put(Integer.class, 3);
        m.put(List.class, Arrays.asList(1, 2, 3));
        
        System.out.println("m.get(Integer.class) = " + m.get(Integer.class));
        System.out.println("m.get(String.class) = " + m.get(String.class));


    }
}
