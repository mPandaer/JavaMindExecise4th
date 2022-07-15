package enumExecise;

public class BaseEnum {
    public static void main(String[] args) {
        Base b = Animal.CAT;
    }
}

interface Base{}

enum Animal implements Base{
    CAT,DOG,DUCK
}
