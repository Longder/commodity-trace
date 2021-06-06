package com.longder.trace;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.InetAddress;

@SpringBootTest
class CommodityTraceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    @SneakyThrows
    void testIP() {
        // 获取本地ip地址
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println("Local HostAddress: " + addr.getHostAddress());
        Long id = 9L;
        String url = String.format("http://%s:5188/commodityInfo/%d", addr.getHostAddress(), id);
        System.out.println("url:" + url);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 200, 200);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
        ImageIO.write(image, "png", baos);//写入流中
        byte[] bytes = baos.toByteArray();//转换成字节
        String qrImage = "data:image/jpeg;base64," + DatatypeConverter.printBase64Binary(bytes);
        System.out.println(qrImage);

    }

}
