package com.rahpouyan.rahayi.demo.model.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "TAVALOD")
@Entity(name = "tavalod")
public class Tavalod {

    @Id
    @Column(columnDefinition = "number")
    private Long id;

    @Column(columnDefinition = "varchar2(50)")
    private String name;

    @Column(columnDefinition = "nvarchar2(20)", nullable = false)
    private String releaseDate;

    @Column(columnDefinition = "nvarchar2(20)", nullable = false)
    private String releaseTime;

    @Column(columnDefinition = "number default 0", nullable = false)
    private int viewNumber;

    @Column(columnDefinition = "varchar2(30)", nullable = false)
    private String type;

    @Column(columnDefinition = "number")
    private Integer enterYear;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_TAVALOD")
    private List<Image> imagesList;

    @Version
    private Long recordVersion;

    private Boolean voice;

    @Column(columnDefinition = "varchar2(20)", nullable = true)
    private String voiceFileName;


    @Column(columnDefinition = "varchar2(20)", nullable = false)
    private String imageFileName;

    @Column(columnDefinition = "NUMBER", nullable = false)
    private int day;

    @Column(columnDefinition = "NUMBER", nullable = false)
    private int month;

    @Column(columnDefinition = "NUMBER", nullable = false)
    private int year;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getEnterYear() {
        return enterYear;
    }

    public void setEnterYear(Integer enterYear) {
        this.enterYear = enterYear;
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

    public Boolean getVoice() {
        return voice;
    }

    public void setVoice(Boolean voice) {
        this.voice = voice;
    }

    public String getVoiceFileName() {
        return voiceFileName;
    }

    public void setVoiceFileName(String voiceFileName) {
        this.voiceFileName = voiceFileName;
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

    @Override
    public String toString() {
        return "Tavalod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", viewNumber=" + viewNumber +
                ", type='" + type + '\'' +
                ", enterYear=" + enterYear +
                ", imagesList=" + imagesList +
                ", recordVersion=" + recordVersion +
                ", voice=" + voice +
                ", voiceFileName='" + voiceFileName + '\'' +
                ", imageFileName='" + imageFileName + '\'' +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
