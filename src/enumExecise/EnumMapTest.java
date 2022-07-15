package enumExecise;

import java.util.EnumMap;

public class EnumMapTest {
    public static void main(String[] args) {
        EnumMap<Number,String> map = new EnumMap<>(Number.class);
        for (Number num : Number.values()) {
            map.put(num,"*"+ num.name()+"*");
        }
        System.out.println(map);
    }
}
