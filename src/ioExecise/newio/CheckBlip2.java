package ioExecise.newio;//: io/Blips.java
// Simple use of Externalizable & a pitfall.

import java.io.*;

import static net.mindview.util.Print.print;

class CheckBlip1 implements Externalizable {
    public CheckBlip1() {
        print("Blip1 Constructor");
    }

    public void writeExternal(ObjectOutput out)
            throws IOException {
        print("Blip1.writeExternal");
    }

    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        print("Blip1.readExternal");
    }
}

public class CheckBlip2 implements Externalizable {
//    CheckBlip2() {
//        print("Blip2 Constructor");
//    }

    public void writeExternal(ObjectOutput out)
            throws IOException {
        print("Blip2.writeExternal");
    }

    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        print("Blip2.readExternal");
    }
}

class CheckBlips {
    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        print("Constructing objects:");
        CheckBlip1 b1 = new CheckBlip1();
        CheckBlip2 b2 = new CheckBlip2();
        ObjectOutputStream o = new ObjectOutputStream(
                new FileOutputStream("Blips.out"));
        print("Saving objects:");
        o.writeObject(b1);
        o.writeObject(b2);
        o.close();
        // Now get them back:
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("Blips.out"));
        print("Recovering b1:");
        b1 = (CheckBlip1) in.readObject();
        // OOPS! Throws an exception:
        print("Recovering b2:");
        b2 = (CheckBlip2) in.readObject();
    }
} /* Output:
Constructing objects:
Blip1 Constructor
Blip2 Constructor
Saving objects:
Blip1.writeExternal
Blip2.writeExternal
Recovering b1:
Blip1 Constructor
Blip1.readExternal
*///:~
