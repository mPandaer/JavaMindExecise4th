package ioExecise.blog.classSerializable;

import java.io.*;

public class RecoverClass {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("person.out")));

        Person.deSerializable(in);
        Class<Person> personClass = (Class<Person>) in.readObject();

        System.out.println(Person.name);

    }
}
