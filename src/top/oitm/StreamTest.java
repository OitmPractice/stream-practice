package top.oitm;

import org.junit.Before;
import org.junit.Test;
import top.oitm.bean.User;

import java.util.*;
import java.util.stream.Collectors;
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

    @Test
    public void testFilterSortedMap() {
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
    public void testSum(){
        //计算所有user年龄总和
        int sum = users.parallelStream().mapToInt(user -> user.getAge()).sum();
        System.out.println(sum);
    }

    @Test // 流的构造和转换
    public void testStreamTranslate(){
        Stream<String> stream = Stream.of("a", "b", "c");

        String[] strArr = {"a", "b", "c"};
        stream = Stream.of(strArr);
        stream = Arrays.stream(strArr);

        List<String> list = Arrays.asList(strArr);
        stream = list.stream();

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
    public void testForEach(){
        users.stream().filter(user -> user.getAge()>15).forEach(user -> System.out.println(user.getUsername()));

        // peek 和 上面的forEach效果一样。不过需要.collect(Collectors.toList());
        List<User> collect = users.stream().filter(user -> user.getAge() > 15).peek(user -> System.out.println(user.getUsername())).collect(Collectors.toList());
    }

    @Test
    public void testOptional(){
        String str = null;
        Optional.ofNullable(str).ifPresent(System.out::println);

        String str2 = "123";
        Optional.ofNullable(str2).ifPresent(System.out::println);

    }

    @Test
    public void testReduce(){
        // 提供一个初始值  然后后面依次按照给出的运算规则进行操作
        int reduce = users.stream().mapToInt(user -> user.getAge()).reduce(0, Integer::sum);
        System.out.println(reduce);
    }


    @Test
    public void testLimitAndSkip(){
        //limit 返回 Stream 的前面 n 个元素；skip 则是扔掉前 n 个元素

        List<User> collect = users.stream().skip(2).limit(4).collect(Collectors.toList());
        System.out.println(collect);
    }








}
