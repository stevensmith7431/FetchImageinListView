package com.example.uploadimageinlistview;

public class MyList {

    String date,session,studno,name,dept,section;
    String image;

    public MyList(String date, String session, String studno, String name, String dept, String section, String image) {
        this.date = date;
        this.session = session;
        this.studno = studno;
        this.name = name;
        this.dept = dept;
        this.section = section;
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getStudno() {
        return studno;
    }

    public void setStudno(String studno) {
        this.studno = studno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
