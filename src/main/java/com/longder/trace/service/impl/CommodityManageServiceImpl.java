package com.longder.trace.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.longder.trace.entity.Commodity;
import com.longder.trace.entity.SysUser;
import com.longder.trace.repository.CommodityRepository;
import com.longder.trace.service.CommodityManageService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

/**
 * 商品管理的服务类
 */
@Service
public class CommodityManageServiceImpl implements CommodityManageService {

    @Resource
    private CommodityRepository commodityRepository;

    /**
     * 根据某用户（卖方）查询
     *
     * @param sysUser
     * @return
     */
    @Override
    public List<Commodity> listCommodities(SysUser sysUser) {
        return commodityRepository.listByUser(sysUser);
    }

    /**
     * 查询所有商品
     *
     * @return
     */
    @Override
    public List<Commodity> listAllCommodities() {
        return commodityRepository.findAll();
    }

    /**
     * 保存一个商品（新增OR修改）
     *
     * @param commodity 商品对象
     * @param seller    卖方
     */
    @Override
    @Transactional
    public void saveOneCommodity(Commodity commodity, SysUser seller) {
        if (ObjectUtils.isEmpty(commodity.getId())) {//新增
            //图片转为base64
            try {
                commodity.setImage("data:image/jpeg;base64," + DatatypeConverter.printBase64Binary(commodity.getImageFile().getBytes()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            //记录卖家，当前用户
            commodity.setSeller(seller);
            commodityRepository.save(commodity);
            this.handleQrCode(commodity);
        } else { // 修改
            Commodity dbCommodity = commodityRepository.getById(commodity.getId());
            dbCommodity.setName(commodity.getName());
            dbCommodity.setUnitPrice(commodity.getUnitPrice());
            dbCommodity.setDealer(commodity.getDealer());
            dbCommodity.setFactory(commodity.getFactory());
            //上传了图片才修改图片
            if (!ObjectUtils.isEmpty(commodity.getImageFile().getOriginalFilename())) {
                try {
                    dbCommodity.setImage("data:image/jpeg;base64," + DatatypeConverter.printBase64Binary(commodity.getImageFile().getBytes()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            commodityRepository.save(dbCommodity);
            this.handleQrCode(dbCommodity);
        }
    }

    /**
     * 处理生成二维码
     */
    @SneakyThrows
    private void handleQrCode(Commodity commodity) {
        // 获取本地ip地址
        InetAddress address = InetAddress.getLocalHost();
        String url = String.format("http://%s:5188/commodityInfo/%d", address.getHostAddress(), commodity.getId());
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 500, 500);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
        ImageIO.write(image, "png", baos);//写入流中
        byte[] bytes = baos.toByteArray();//转换成字节
        String qrImage = "data:image/jpeg;base64," + DatatypeConverter.printBase64Binary(bytes);
        commodity.setQrCode(qrImage);
        commodityRepository.save(commodity);
    }

    /**
     * 查询获取一个商品信息（详情）
     *
     * @param commodityId 商品id
     * @return
     */
    @Override
    public Commodity getOneCommodity(Long commodityId) {
        return commodityRepository.getById(commodityId);
    }

    /**
     * 扫描统计数+1
     *
     * @param commodityId
     */
    @Override
    @Transactional
    public void scanCountPlus(Long commodityId) {
        Commodity commodity = commodityRepository.getById(commodityId);
        if (!ObjectUtils.isEmpty(commodity)) {
            commodity.setScanCount(commodity.getScanCount() + 1);
            commodityRepository.save(commodity);
        }
    }
}
