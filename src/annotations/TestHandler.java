package annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class TestHandler {
    public static void main(String[] args) {
        handle(UseTest.class);
    }

    public static void handle (Class<?> aClass) {
        Test testAnnotations = aClass.getAnnotation(Test.class);
        if (testAnnotations == null) {
            System.err.println("处理的类，没有用Test注解修饰");
            return;
        }
        System.out.format("className :%s --- name: %s value: %s\n",
                aClass.getSimpleName(),testAnnotations.name(),testAnnotations.value());

        Field[] fields =  aClass.getDeclaredFields();
        ArrayList<Annotation> annotations = new ArrayList<>();
        for (Field field : fields) {
            Annotation[] ans = field.getAnnotations();
            for (Annotation an : ans ){
                if (an instanceof Test) {
                    annotations.add(an);
                }
            }

        }
        System.out.println(annotations);
    }

}
