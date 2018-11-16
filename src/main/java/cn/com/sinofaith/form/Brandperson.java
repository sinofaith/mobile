package cn.com.sinofaith.form;

public class Brandperson {
    private String brand_name;
    private String role_name;
    private long num;
    private long qfriendNum;
    private long qltjlNum;
    private long wfriendNum;
    private long wltjlNum;
    private long dxNum;
    private long thqdNum;
    private long txlNum;

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long qfriendNum,long qltjlNum,long wfriendNum,long wltjlNum,long dxNum,long thqdNum,long txlNum) {
        this.num = qfriendNum+qltjlNum+wfriendNum+wltjlNum+dxNum+thqdNum+txlNum;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public long getQfriendNum() {
        return qfriendNum;
    }

    public void setQfriendNum(long qfriendNum) {
        this.qfriendNum = qfriendNum;
    }

    public long getQltjlNum() {
        return qltjlNum;
    }

    public void setQltjlNum(long qltjlNum) {
        this.qltjlNum = qltjlNum;
    }

    public long getWfriendNum() {
        return wfriendNum;
    }

    public void setWfriendNum(long wfriendNum) {
        this.wfriendNum = wfriendNum;
    }

    public long getWltjlNum() {
        return wltjlNum;
    }

    public void setWltjlNum(long wltjlNum) {
        this.wltjlNum = wltjlNum;
    }

    public long getDxNum() {
        return dxNum;
    }

    public void setDxNum(long dxNum) {
        this.dxNum = dxNum;
    }

    public long getThqdNum() {
        return thqdNum;
    }

    public void setThqdNum(long thqdNum) {
        this.thqdNum = thqdNum;
    }

    public long getTxlNum() {
        return txlNum;
    }

    public void setTxlNum(long txlNum) {
        this.txlNum = txlNum;
    }
}
