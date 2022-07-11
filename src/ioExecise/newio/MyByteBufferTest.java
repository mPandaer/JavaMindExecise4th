package ioExecise.newio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MyByteBufferTest {
    public static final int SIZE = 1024;
    public static void display(String str) throws IOException {
        File file = new File("text.txt");
//        file.createNewFile();
        FileChannel out = new FileOutputStream(file).getChannel();
//        way1:
//        out.write(ByteBuffer.wrap(str.getBytes(StandardCharsets.UTF_16BE)));

//        way2:
        ByteBuffer bf = ByteBuffer.allocate(SIZE);
        bf.asCharBuffer().put(str);
        out.write(bf);
        out.close();
        FileChannel in = new FileInputStream(file).getChannel();
        bf.clear();
        while(in.read(bf) != -1){
            bf.flip();
            System.out.println(bf.asCharBuffer());
            bf.clear();
        }
    }


    public static void main(String[] args) throws IOException {
        display("YYDS");
    }
}
