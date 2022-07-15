package ioExecise.blog;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

/**
 * 比较Data包装流和Buffer之间的速度
 */
public class SpeedTest {


    public static void writeIntStream (File f) throws IOException {
        Random random = new Random(47);
        DataOutputStream out = new DataOutputStream(new FileOutputStream(f));
        System.out.println("开始写数据");
        long start = System.nanoTime();
        for (int i = 0; i< 100000; i++) {
            out.writeInt(random.nextInt(20));
        }
        long duration = System.nanoTime() - start;
        System.out.format("完成耗时：%.2f\n",duration / 1.0e9);
        out.close();

    }

    public static void readIntStream (File f) throws IOException {
        DataInputStream in = new DataInputStream(new FileInputStream(f));
        System.out.println("开始读数据");
        long start = System.nanoTime();
        for (int i = 0; i< 100000; i++) {
            in.readInt();
        }
        long duration = System.nanoTime() - start;
        System.out.format("完成耗时：%.2f\n",duration / 1.0e9);
        in.close();
    }


    public static void writeBuffer(File f) throws IOException {
        Random random = new Random(47);
        ByteBuffer bf = ByteBuffer.allocate(100000);
        FileChannel fc = new FileOutputStream(f).getChannel();
        System.out.println("开始写数据");
        long start = System.nanoTime();
        for (int i = 0; i< 100000; i++) {
            bf.asIntBuffer().put(random.nextInt(20));
        }
        fc.write(bf);
        long duration = System.nanoTime() - start;
        System.out.format("完成耗时：%.2f\n",duration / 1.0e9);
    }

    public static void readBuffer(File f) throws IOException {
        ByteBuffer bf = ByteBuffer.allocate(100000);
        FileChannel fc = new FileInputStream(f).getChannel();
        System.out.println("开始读数据");
        long start = System.nanoTime();
        fc.read(bf);
        bf.flip();
        for (int i = 0; i< 100000; i++) {
            bf.asIntBuffer().get();
        }
        long duration = System.nanoTime() - start;
        System.out.format("完成耗时：%.2f\n",duration / 1.0e9);

    }

    public static void main(String[] args) throws IOException {
        File f = new File("speedTest.txt");
        boolean isDelete;
        writeIntStream(f);
        readIntStream(f);
        System.out.println(f.delete());

        writeBuffer(f);
        readBuffer(f);
        f.deleteOnExit();
    }
}
