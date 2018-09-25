import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class param<T1, T2> {
    private Class<T1> entityClass;

    public param() {
        Type type = getClass().getGenericSuperclass();
        Type[] trueType = (((ParameterizedType) type)).getActualTypeArguments();
        int len = trueType.length;
        for (int i = 0; i < len; i++) {
            System.out.println(trueType[i] + " ");
        }
        this.entityClass = (Class<T1>) trueType[1];
        B t = new B();
        type = t.getClass().getGenericSuperclass();
        System.out.println("B is A's super class : " + ((ParameterizedType) type).getActualTypeArguments().length);
    }

    public static void main(String[] args) {
        new param<>();
    }

    class A {
    }

    class B extends A {
    }
}
