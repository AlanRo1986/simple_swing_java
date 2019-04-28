package system.model;

/**
 * Created by alan on 2019/4/15.
 */

import system.ano.Id;
import system.ano.Table;

/**
 * CREATE TABLE `lx_user` (
 * `id` int(11) NOT NULL AUTO_INCREMENT,
 * `username` varchar(255) DEFAULT NULL,
 * `loginPassword` varchar(32) DEFAULT NULL,
 * `nickname` varchar(50) DEFAULT NULL,
 * `realName` varchar(50) DEFAULT NULL,
 * `email` varchar(50) DEFAULT NULL,
 * `mobile` varchar(11) DEFAULT NULL,
 * `sex` varchar(50) DEFAULT 'UNSPECIFIED',
 * `age` int(3) DEFAULT '0',
 * `createTime` int(11) DEFAULT '0',
 * PRIMARY KEY (`id`),
 * KEY `id` (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4;
 */

@Table(name = "lx_user")
public class LxUser {

    @Id
    private Integer id;

    private String username;
    private String loginPassword;
    private String nickname;
    private String realName;
    private String email;
    private String mobile;
    private String sex;
    private Integer age;
    private Integer createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return String.format("{id:%s;username:%s;loginPassword:%s;sex:%s;age:%s}", this.id, this.username, this.loginPassword, this.sex, this.age);
    }

}
