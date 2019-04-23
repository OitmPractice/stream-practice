package top.oitm.bean;

/**
 * @Description:
 * @Author: song_shu_ran
 * @Date: 2019-04-23 17:13
 */

public class User {

    private String id;
    private String username;
    private String address;
    private int age;
    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public User(String id, String username, String address, int age) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.age = age;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
