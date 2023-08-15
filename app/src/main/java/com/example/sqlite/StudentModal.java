package com.example.sqlite;

public class StudentModal {
    private String regNo;
    private String name;
    private String email;
    private int id;
    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }
    public String getName() { return name;}
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public StudentModal(String regNo, String name, String email)
    {
        this.regNo = regNo;
        this.name = name;
        this.email = email;
    }
}