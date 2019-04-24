package top.oitm;

import org.junit.Before;
import org.junit.Test;
import top.oitm.bean.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: song_shu_ran
 * @Date: 2019-04-23 16:17
 */

public class StreamTest {


    private  ArrayList<User> users = new ArrayList<>();
    @Before
    public void initUsers(){
        for (int i = 0; i < 10; i++) {
            User user = new User(i + "", "oitm" + i, "shanghai" + i, i + 10);
            user.setHeight(i % 2 == 0 ? 180 : 170);
            users.add(user);
        }
    }


    @Test // 流的构造和转换
    public void testStreamTranslate(){
        Stream<String> stream = Stream.of("1", "2", "3");

        String[] strArr = {"a", "b", "c"};
        stream = Stream.of(strArr);
        stream = Arrays.stream(strArr);

        List<String> list = Arrays.asList(strArr);
        stream = list.stream();

        // 基数值类型
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);//遍历数组
        IntStream.range(1, 3).forEach(System.out::println);// 遍历1-3的开区间
        IntStream.rangeClosed(1, 3).forEach(System.out::println);//遍历1-3的闭区间
    }


    @Test
    public void testMap(){
        // 获取所用user的username并返回list
        List<String> names = users.stream().map(user -> user.getUsername()).collect(Collectors.toList());

        //把所有user年龄改为18 并返回
        List<User> collect = users.stream().map(user -> {
            user.setAge(18);
            return user;
        }).collect(Collectors.toList());
    }


    @Test
    public void testFlatMap(){
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        List<Integer> collect = inputStream.flatMap(list -> list.stream()).collect(Collectors.toList());
        System.out.println(collect);
    }


    @Test
    public void testFilter() {
        List<User> collect = users.parallelStream()
                .filter(user -> {
                    return user.getHeight() > 170 && user.getAge() > 15;
                }).collect(Collectors.toList());
        System.out.println(collect);
    }


    @Test
    public void testForEach(){
        users.stream().filter(user -> user.getAge()>15).forEach(user -> System.out.println(user.getUsername()));

        // peek 和 上面的forEach效果一样。不过需要.collect(Collectors.toList());
        List<User> collect = users.stream().filter(user -> user.getAge() > 15).peek(user -> System.out.println(user.getUsername())).collect(Collectors.toList());
    }


    @Test
    public void testFindFirst(){
        Optional<User> first = users.stream().findFirst();
        System.out.println(first.get());

        Optional<User> any = users.stream().findAny();
        System.out.println(any.get());
    }

    @Test
    public void testReduce(){
        // 提供一个初始值  然后后面依次按照给出的运算规则进行操作
        int sunmAllUserAge = users.stream().mapToInt(user -> user.getAge()).reduce(0, Integer::sum);
        System.out.println(sunmAllUserAge);
    }

    @Test
    public void testLimitAndSkip(){
        //limit 返回 Stream 的前面 n 个元素；skip 则是扔掉前 n 个元素
        List<User> collect = users.stream().skip(2).limit(4).collect(Collectors.toList());
        System.out.println(collect);
    }


    @Test
    public void testSorted() {
        //找出age>15的， 过滤出height大于170的，根据名字排序，最后返回这些user的id的数组
        List<String> collect = users.parallelStream()
                .filter(user -> {
                    return user.getHeight() > 170 && user.getAge() > 15;
                })
                .sorted(Comparator.comparing(User::getUsername).reversed())
                .map(user -> user.getId()).collect(Collectors.toList());
        System.out.println(collect);
    }


    @Test
    public void testDistinct(){
        ArrayList<String> list = new ArrayList<>();
        list.add("oitm");
        list.add("oitm1");
        list.add("oitm2");
        list.add("oitm");
        System.out.println(list);

        List<String> collect = list.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);
    }


    @Test
    public void testMatch(){
        boolean b = users.stream().allMatch(user -> user.getAge() > 12);
        System.out.println(b);

        boolean b1 = users.stream().anyMatch(user -> user.getAge() > 15);
        System.out.println(b1);

        boolean b2 = users.stream().noneMatch(user -> user.getHeight() > 190);
        System.out.println(b2);
    }





    @Test
    public void testSum(){
        //计算所有user年龄总和
        int sum = users.parallelStream().mapToInt(user -> user.getAge()).sum();
        System.out.println(sum);
    }


    @Test
    public void testOptional(){
        String str = null;
        Optional.ofNullable(str).ifPresent(System.out::println);

        String str2 = "123";
        Optional.ofNullable(str2).ifPresent(System.out::println);

    }










}
