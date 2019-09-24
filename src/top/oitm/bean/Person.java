package top.oitm.bean;

/**
 * @Description:
 * @Author: song_shu_ran
 * @Date: 2019-09-24 18:44
 */

public class Person {
    private String name;
    private int age;
    private int rand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRand() {
        return rand;
    }

    public void setRand(int rand) {
        this.rand = rand;
    }

    public Person(int age, String name, int rand) {
        this.name = name;
        this.age = age;
        this.rand = rand;
    }
}
