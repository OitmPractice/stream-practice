package top.oitm;

import org.junit.Test;
import top.oitm.bean.Person;
import top.oitm.supplier.PersonSupplier;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Description: 进阶使用
 * @Author: song_shu_ran
 * @Date: 2019-09-24 17:14
 */

public class StreamTest2 {
    //自己生成流
    @Test
    public void test() {
        Random seed = new Random();
        Supplier<Integer> random = seed::nextInt;
        Stream.generate(random).limit(10).forEach(System.out::println);

        IntStream.generate(() -> (int) (System.nanoTime() % 100))
                .limit(10).forEach(System.out::println);
    }

    // 自己实现Supplier
    @Test
    public void testSupplierImpl() {
        Stream.generate(new PersonSupplier())
                .limit(10)
                .forEach(p -> System.out.println(p.getName() + "," + p.getRand()));
    }


    @Test
    public void testIterate() {
        Stream.iterate(2, n -> n * n)
                .limit(5)
                .forEach(x -> System.out.print(x + " "));
    }

    // reduction 操作
    @Test
    public void testGroupingBy(){
        Map<Integer, List<Person>> collect = Stream.generate(new PersonSupplier())
                .limit(100)
                .collect(Collectors.groupingBy(Person::getRand));
        Iterator<Map.Entry<Integer, List<Person>>> iterator = collect.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Person>> persons = (Map.Entry) iterator.next();
            System.out.println("Age " + persons.getKey() + " = " + persons.getValue());
        }
    }


}
