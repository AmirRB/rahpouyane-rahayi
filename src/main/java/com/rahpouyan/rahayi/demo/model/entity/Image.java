package com.rahpouyan.rahayi.demo.model.entity;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Entity(name = "image")
@Table(name = "IMAGE")
public class Image {

    @Id()
    @Column(columnDefinition = "number")
    private Long id;

    @Column(columnDefinition = "nclob")
    private String text;

    @Column(columnDefinition = "number", nullable = false)
    private int width;

    @Column(columnDefinition = "number", nullable = false)
    private int height;

    @Column(columnDefinition = "varchar2(20)")
    private String fileName;

    @Version
    private int recodVersion;

    public Image() {

    }

    public Image(File file) {
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            this.width = bufferedImage.getWidth();
            this.height = bufferedImage.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getRecodVersion() {
        return recodVersion;
    }

    public void setRecodVersion(int recodVersion) {
        this.recodVersion = recodVersion;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", fileName='" + fileName + '\'' +
                ", recodVersion=" + recodVersion +
                '}';
    }
}
