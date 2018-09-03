package cn.com.sinofaith.bean;

import javax.persistence.*;

/**
 * Created by xs on 2018/8/31.
 */
@Entity
@Table(name="QQ_HTML",schema = "",catalog = "")
public class QqHtmlEntity {
    private int id;
    private String sender;
    private String senddate;
    private String addressee;
    private String content;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Basic
    @Column(name = "sender")
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
    @Basic
    @Column(name = "sederdate")
    public String getSenddate() {
        return senddate;
    }

    public void setSenddate(String senddate) {
        this.senddate = senddate;
    }
    @Basic
    @Column(name = "addressee")
    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String address) {
        this.addressee = address;
    }
    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QqHtmlEntity that = (QqHtmlEntity) o;

        if (id != that.id) return false;
        if (sender != null ? !sender.equals(that.sender) : that.sender != null) return false;
        if (senddate != null ? !senddate.equals(that.senddate) : that.senddate != null) return false;
        if (addressee != null ? !addressee.equals(that.addressee) : that.addressee != null) return false;
        return content != null ? content.equals(that.content) : that.content == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (senddate != null ? senddate.hashCode() : 0);
        result = 31 * result + (addressee != null ? addressee.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
