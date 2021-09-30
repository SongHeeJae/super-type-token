package kuke.supertypetoken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class SuperTypeToken {

    static class TypesafeMap {
        Map<Type, Object> map = new HashMap<>();

        <T> void put(TypeReference<T> tr, T value) {
            map.put(tr.type, value);
        }

        <T> T get(TypeReference<T> tr) {
            if(tr.type instanceof Class<?>)
                return ((Class<T>) tr.type).cast(map.get(tr.type));
            else // ParameterizedType
                return ((Class<T>)((ParameterizedType)tr.type).getRawType()).cast(map.get(tr.type));
        }
    }

    static class TypeReference<T> {
        Type type;

        public TypeReference() {
            final Type stype = getClass().getGenericSuperclass();
            if(stype instanceof ParameterizedType) {
                this.type = ((ParameterizedType)stype).getActualTypeArguments()[0];
            } else throw new RuntimeException();
        }
    }

    // 익명 클래스의 인스턴스를 넘김으로써 제너릭 정보를 풀로 주는 방식을 super type token이라고 함.

    public static void main(String[] args) throws NoSuchFieldException {
        TypeReference t = new TypeReference<String>(){}; // 상속시키면 런타임에 알 수 있음
        System.out.println("t.type = " + t.type);

        final TypesafeMap m = new TypesafeMap();
        m.put(new TypeReference<String>(){}, "Value");
        m.put(new TypeReference<Integer>(){}, 3);
        m.put(new TypeReference<List<Integer>>(){}, Arrays.asList(1, 2, 3));
        m.put(new TypeReference<List<String>>(){}, Arrays.asList("a", "b", "c"));
        System.out.println("m.get(Integer.class) = " + m.get(new TypeReference<Integer>(){}));
        System.out.println("m.get(String.class) = " + m.get(new TypeReference<String>(){}));
        System.out.println("m.get(List<Integer>.class) = " + m.get(new TypeReference<List<Integer>>(){}));
        System.out.println("m.get(List<String>.class) = " + m.get(new TypeReference<List<String>>(){}));


//        Type t = new Sup<List<String>>() {}.getClass().getGenericSuperclass();
//        ParameterizedType ptype = (ParameterizedType)t;
//        System.out.println("ptype.getActualTypeArguments() = " + ptype.getActualTypeArguments()[0]); // List<String>

//        Sup<String> s = new Sup<>();
//        System.out.println(s.getClass().getDeclaredField("value").getType()); // Object

//        Sub b = new Sub();
//        Type t = b.getClass().getGenericSuperclass();
//        ParameterizedType ptype = (ParameterizedType)t;
//        System.out.println("ptype.getActualTypeArguments() = " + ptype.getActualTypeArguments()[0]); // List<String>
    }

//    static class Sup<T> {
//        T value;
//    }
}
