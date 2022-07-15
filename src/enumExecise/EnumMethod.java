package enumExecise;


public class EnumMethod {
    public static void main(String[] args) {
        System.out.println(Test.NUM1.getClass());
        System.out.println(Tool.IDEA.getClass());
    }
}

enum Tool {
    IDEA{
        @Override
        void say() {
            System.out.println("IDEA");
        }
    },
    PY{
        @Override
        void say() {
            System.out.println("PY");
        }
    },
    VSCODE{
        @Override
        void say() {
            System.out.println("VSCODE");
        }
    },
    TEXT{
        @Override
        void say() {
            System.out.println("TEXT");
        }
    };
    abstract  void say();
}

enum Test {
    NUM1,NUM2,NUM3
}
