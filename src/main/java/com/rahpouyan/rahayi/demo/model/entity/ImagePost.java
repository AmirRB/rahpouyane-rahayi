package com.rahpouyan.rahayi.demo.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "imagePost")
@Table(name = "IMAGE_POST")
public class ImagePost {

    @Id()
    @Column(columnDefinition = "number")
    private Long id;

    @Column(columnDefinition = "nvarchar2(200)", nullable = false)
    private String title;

    private Boolean voice;

    @Column(columnDefinition = "varchar2(20)")
    private String fileName;

    @Column(columnDefinition = "nvarchar2(20)", nullable = true)
    private String releaseDate;

    @Column(columnDefinition = "nvarchar2(20)", nullable = true)
    private String releaseTime;

    @Column(columnDefinition = "number default 0", nullable = true)
    private int viewNumber;

    @Column(columnDefinition = "varchar2(20)", nullable = true)
    private String type;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_IMAGEPOST")
    private List<Image> imagesList;

    @Version
    private Long recordVersion;

    @Column(columnDefinition = "varchar2(20)", nullable = false)
    private String imageFileName;

    @Column(columnDefinition = "NUMBER", nullable = false)
    private int day;

    @Column(columnDefinition = "NUMBER", nullable = false)
    private int month;

    @Column(columnDefinition = "NUMBER", nullable = false)
    private int year;

    @Column(columnDefinition = "nvarchar2(50)")
    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVoice() {
        return voice;
    }

    public void setVoice(Boolean voice) {
        this.voice = voice;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(int viewNumber) {
        this.viewNumber = viewNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Image> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<Image> imagesList) {
        this.imagesList = imagesList;
    }

    public Long getRecordVersion() {
        return recordVersion;
    }

    public void setRecordVersion(Long recordVersion) {
        this.recordVersion = recordVersion;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "ImagePost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", voice=" + voice +
                ", fileName='" + fileName + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", viewNumber=" + viewNumber +
                ", type='" + type + '\'' +
                ", imagesList=" + imagesList +
                ", recordVersion=" + recordVersion +
                ", imageFileName='" + imageFileName + '\'' +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", city='" + city + '\'' +
                '}';
    }
}
