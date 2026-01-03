package com.java.hospital_sample.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    private String name;
    private int age;
    private String address;
    private int weight;
    private String email;
    private String password;

    public User() {}

    public User(Long userid, String name, int age, String address, int weight, String email, String password) {
        this.userid = userid;
        this.name = name;
        this.age = age;
        this.address = address;
        this.weight = weight;
        this.email = email;
        this.password = password;
    }

    // Getters and setters
    public Long getUserid() { return userid; }
    public void setUserid(Long userid) { this.userid = userid; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getWeight() { return weight; }
  public void setWeight(int weight) {
	  this.weight=weight;
	  }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
