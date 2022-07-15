package enumExecise;

import java.util.Arrays;

public class EnumClass {
    public static void main(String[] args) {
        for (Language lan : Language.class.getEnumConstants()){
            System.out.println(lan);
        }
        System.out.println(Arrays.toString(EnumClass.class.getEnumConstants()));
    }
}
