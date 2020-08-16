package com.rahpouyan.rahayi.demo.model.entity;

import javax.persistence.*;

@Entity(name = "namayandegi")
@Table(name = "NAMAYANDEGI")
public class Namayandegi {

    @Id
    private Long id;

    @Column(columnDefinition = "NVARCHAR2(30)",nullable = false)
    private String city;

    @Column(columnDefinition = "NVARCHAR2(255)" , nullable = false)
    private String address;

    @Column( columnDefinition = "NVARCHAR2(255)")
    private String phone;

    @Column( columnDefinition = "NVARCHAR2(255)")
    private String time;

    @Column( columnDefinition = "NVARCHAR2(255)")
    private String description;

    @Column( columnDefinition = "NVARCHAR2(100)", nullable = false)
    private String name;

    @Version
    private Long recordVersion;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRecordVersion() {
        return recordVersion;
    }

    public void setRecordVersion(Long recordVersion) {
        this.recordVersion = recordVersion;
    }

    @Override
    public String toString() {
        return "Namayandegi{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", recordVersion=" + recordVersion +
                '}';
    }
}
