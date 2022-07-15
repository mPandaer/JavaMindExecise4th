package ioExecise.blog.classSerializable;


import java.io.*;
import java.util.concurrent.TimeUnit;

public class ClassTest {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        File f = new File("person.out");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        Person.name = "update";

        Person.serializable(out);
        out.writeObject(Person.class);

    }
}

class Person {
    public static String name = "person";
    public static void serializable(ObjectOutputStream out) throws IOException {
        out.writeObject(name);
    }
    public static void deSerializable(ObjectInputStream in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
    }
}
