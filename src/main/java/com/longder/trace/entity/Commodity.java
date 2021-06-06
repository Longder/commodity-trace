package com.longder.trace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 商品实体类
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "COMMODITY")
public class Commodity extends BaseIdEntity {
    /**
     * 商品名称
     */
    @Column(name = "name_")
    private String name;

    /**
     * 经销商
     */
    @Column(name = "dealer_")
    private String dealer;

    /**
     * 生产地
     */
    @Column(name = "factory")
    private String factory;

    /**
     * 商品单价
     */
    @Column(name = "unit_price_")
    private BigDecimal unitPrice;

    /**
     * 商品图片数据 ，BASE64值
     */
    @Lob
    @Column(name = "image_", columnDefinition = "longtext")
    private String image;

    /**
     * 商品二维码图片，BASE64值
     */
    @Lob
    @Column(name = "qr_code_", columnDefinition = "longtext")
    private String qrCode;

    /**
     * 商品二维码被扫描的次数（默认0次）
     */
    @Column(name = "scan_count_")
    private Integer scanCount = 0;

    /**
     * 卖方，关联用户表
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id_")
    @JsonIgnore
    private SysUser seller;

    /**
     * 上传的图片文件（页面传递，不持久化）
     */
    @Transient
    private MultipartFile imageFile;


}
