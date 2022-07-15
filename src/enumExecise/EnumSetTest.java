package enumExecise;

import java.util.EnumSet;

public class EnumSetTest {
    public static void main(String[] args) {
        EnumSet<Number> nums = EnumSet.allOf(Number.class);
        System.out.println(nums);
        nums = EnumSet.noneOf(Number.class);
        nums.add(Number.NUM1);
        nums.add(Number.NUM3);
        nums.add(Number.NUM2);
        nums.add(Number.NUM6);
        nums.add(Number.NUM5);
        System.out.println(nums);
    }
}

enum Number {
    NUM1,NUM2,NUM3,NUM4,NUM5,NUM6
}
