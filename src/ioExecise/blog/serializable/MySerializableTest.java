package ioExecise.blog.serializable;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class MySerializableTest {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        File f = new File("bird.out");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        out.writeObject(new Bird("bird",18));
        TimeUnit.SECONDS.sleep(1);
        System.out.println("---------------------------------");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
        System.out.println(in.readObject());
    }
}

class Bird implements Serializable {
    transient Integer age;
    transient String name;
    public Bird (){
        System.out.println("Bird普通的构造器");
    }
    public Bird(String name,int age) {
        System.out.println("Bird带参的构造器");
        this.age = age;
        this.name = name;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(age);
        out.writeObject(name);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        age = (Integer) in.readObject();
        name = (String) in.readObject();
    }

    @Override
    public String toString() {
        return String.format("name: %s age: %d",name, age);
    }
}