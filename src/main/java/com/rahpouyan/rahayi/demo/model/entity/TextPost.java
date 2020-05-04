package com.rahpouyan.rahayi.demo.model.entity;


import javax.persistence.*;

@Entity(name = "textPost")
@Table(name = "TEXT_POST")
public class TextPost {

    @Id()
    @Column(columnDefinition = "number")
    private Long id;

    @Column(columnDefinition = "nvarchar2(200)", nullable = false)
    private String title;

    private Boolean voice;

    @Column(columnDefinition = "nclob", nullable = false)
    private String text;

    @Column(columnDefinition = "nvarchar2(20)", nullable = false)
    private String releaseDate;

    @Column(columnDefinition = "varchar2(20)")
    private String fileName;

    @Column(columnDefinition = "nvarchar2(20)", nullable = false)
    private String releaseTime;

    @Column(columnDefinition = "number default 0", nullable = false)
    private int viewNumber;

    @Column(columnDefinition = "varchar2(10)", nullable = false)
    private String type;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    @Column(columnDefinition = "varchar2(20)", nullable = false)
    private String tarikh;

    @Version
    private Long recordVersion;

    @Column(columnDefinition = "nvarchar2(50)")
    private String rahavard;

    @Column(columnDefinition = "nvarchar2(50)")
    private String rahamooz;

    @Column(columnDefinition = "nvarchar2(50)")
    private String rahnegar;

    @Column(columnDefinition = "nvarchar2(50)")
    private String namayandegi;


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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public Long getRecordVersion() {
        return recordVersion;
    }

    public void setRecordVersion(Long recordVersion) {
        this.recordVersion = recordVersion;
    }

    public String getRahavard() {
        return rahavard;
    }

    public void setRahavard(String rahavard) {
        this.rahavard = rahavard;
    }

    public String getRahamooz() {
        return rahamooz;
    }

    public void setRahamooz(String rahamooz) {
        this.rahamooz = rahamooz;
    }

    public String getRahnegar() {
        return rahnegar;
    }

    public void setRahnegar(String rahnegar) {
        this.rahnegar = rahnegar;
    }

    public String getNamayandegi() {
        return namayandegi;
    }

    public void setNamayandegi(String namayandegi) {
        this.namayandegi = namayandegi;
    }

    @Override
    public String toString() {
        return "TextPost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", voice=" + voice +
                ", text='" + text + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", fileName='" + fileName + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", viewNumber=" + viewNumber +
                ", type='" + type + '\'' +
                ", image=" + image +
                ", tarikh='" + tarikh + '\'' +
                ", recordVersion=" + recordVersion +
                ", rahavard='" + rahavard + '\'' +
                ", rahamooz='" + rahamooz + '\'' +
                ", rahnegar='" + rahnegar + '\'' +
                ", namayandegi='" + namayandegi + '\'' +
                '}';
    }
}
