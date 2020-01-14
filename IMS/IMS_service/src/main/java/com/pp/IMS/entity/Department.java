package com.pp.IMS.entity;

/**
 * @author laijs
 * @date 2019-12-26-20:58
 */
public class Department {
    private int id;
    private String name;
    private String address;

    public Department() {
    }

    public Department(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
