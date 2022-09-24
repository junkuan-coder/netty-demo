package netty.c1;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author 王均宽
 * @version 1.0
 * @since 24/9/2022 下午 3:05
 */
@Slf4j
public class TestByteBuffer {
	public static void main(String[] args) {
		// 1.准备FileChannel
		// 1.输入输出流 2.RandomAccessFile
		try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
			// 准备缓冲区buffer，用于接收输入
			ByteBuffer buffer = ByteBuffer.allocate(10);
			// 切换buffer读模式，进行读取
			while (true) { // 是否还有剩余数据
				// 从channel读取数据，向buffer中写入字节
				int len = channel.read(buffer);
				log.debug("读取到的字节数{}", len);
				if (len == -1) {
					break;
				}
				// 切换读模式
				buffer.flip();
				while (buffer.hasRemaining()) {
					byte b = buffer.get();
					log.debug("读取到的字节{}", (char) b);
				}
				// 切换为写模式
				buffer.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
		}
	}
}
