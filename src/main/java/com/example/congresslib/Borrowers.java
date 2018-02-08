package com.example.congresslib;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Borrowers {
    @Id
    @Autowired
    private long studid;

    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    private String borrowedbook;

    public long getStudid() {
        return studid;
    }

    public void setStudid(long studid) {
        this.studid = studid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBorrowedbook() {
        return borrowedbook;
    }

    public void setBorrowedbook(String borrowedbook) {
        this.borrowedbook = borrowedbook;
    }
}
