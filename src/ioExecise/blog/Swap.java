package ioExecise.blog;


import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class Swap {
    public static void swap(CharBuffer buffer) {
        while (buffer.hasRemaining()) {
            buffer.mark();
            char c1 = buffer.get();
            char c2 = buffer.get();
            buffer.reset();
            buffer.put(c2);
            buffer.put(c1);
        }
    }

    public static void main(String[] args) {
        String str = "abcd";
        char[] chars = str.toCharArray();
        CharBuffer cb = ByteBuffer.allocate(str.length() * 2).asCharBuffer();
        cb.put(chars);
        cb.rewind();
        System.out.println(cb);
        swap(cb);
        System.out.println(cb.rewind());

    }
}
