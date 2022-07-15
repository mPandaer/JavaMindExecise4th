package enumExecise;

public class EnumTest {
    public static void main(String[] args) {
        for (Language lan : Language.values()) {
            System.out.println(lan.name());
            choose(lan);
            System.out.println("----------");
        }

    }
    public static void choose(Language lan) {
        switch (lan) {
            case JAVA:
                System.out.println("java yyds");
                return;
            case KOTLIN:
                System.out.println("kotlin yyds");
                return;
            case PYTHON:
                System.out.println("python yyds");
                return;
            case C:
                System.out.println("c yyds");
        }
    }
}

enum Language {
    JAVA,KOTLIN,PYTHON,C
}
