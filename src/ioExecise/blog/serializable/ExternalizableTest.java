package ioExecise.blog.serializable;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class ExternalizableTest {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        File f = new File("cat.out");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        out.writeObject(new Cat("tom",18));
        TimeUnit.SECONDS.sleep(2);
        System.out.println("---------------------------------");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
        System.out.println(in.readObject());
    }
}

class Cat implements Externalizable {
    String name;
    int age;
    {
        System.out.println("初始代码块");
    }
    /*public*/ Cat(){
        System.out.println("Cat的默认构造器");
    }
    public Cat(String name, int age) {
        System.out.println("带参的构造器");
        this.name = name;
        this.age = age;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(age);
        out.writeObject(name);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        age = in.readInt();
        name = (String) in.readObject();
    }

    @Override
    public String toString() {
        return String.format("name: %s age: %d\n",name,age);
    }
}
