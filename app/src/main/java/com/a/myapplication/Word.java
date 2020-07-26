package com.a.myapplication;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class Word {
    @Id
    private long id;
    String persion;
    String greece;
    String type;
    @Generated(hash = 896283387)
    public Word(long id, String persion, String greece, String type) {
        this.id = id;
        this.persion = persion;
        this.greece = greece;
        this.type = type;
    }
    @Generated(hash = 3342184)
    public Word() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getPersion() {
        return this.persion;
    }
    public void setPersion(String persion) {
        this.persion = persion;
    }
    public String getGreece() {
        return this.greece;
    }
    public void setGreece(String greece) {
        this.greece = greece;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
