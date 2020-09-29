package com.boot.utils;

import com.boot.bean.Student;

import java.time.LocalDate;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/9/25 10:48
 * <h>Collectors工具类</h>
 */
public class CollectorsUtils {
    /*** 以下为数据收集部分,包含toList,toSet,toCollections,toMap等方法*/
    public void testCollectors(List<Student> studentList) {
        //toCollection,toList,toSet方法,toList和toSet固定了返回的集合的类型,toCollection接受一个集合类型作为入参
        List<Integer> integers = Stream.of(1, 2, 3, 4, 5, 6, 7).collect(Collectors.toCollection(ArrayList::new));
        Set<Integer> sets = Stream.of(1, 2, 3, 4, 5, 6, 6).collect(Collectors.toCollection(HashSet::new));
        List<Integer> integers1 = Stream.of(1, 2, 3, 4, 5, 6, 6).collect(Collectors.toList());
        Set<Integer> sets2 = Stream.of(1, 2, 3, 4, 5, 6, 6).collect(Collectors.toSet());
        //toMap  toConcurrentMap ,其中toConcurrentMap支持并行收集,由于集合特性,最少参数必须包含key-value
        //在实体映射中,需要通过某个字段来映射本身时,可以通过Function.identify()来指定value为对象自己
        Map<Integer, Student> student1Map = studentList.stream().collect(Collectors.toMap(Student::getId, Function.identity()));
        //并行收集
        Map<Integer, Student> student1Map1 = studentList.stream().parallel().collect(Collectors.toConcurrentMap(Student::getId, Function.identity()));
        //收集字段映射集合
        Map<Integer, String> studentMap2 = studentList.stream().collect(Collectors.toMap(Student::getId, Student::getName));
        //针对于映射的实体中,key不唯一的情况,可以toMap的第三个参数来指定哪个值是需要的
        studentList.add(new Student(1, "wbt2", LocalDate.now().minusDays(1)));
        //通过BinaryOperator来选择比较,需要注意的是这里的BinaryOperator比较的对象是相同Key值映射下的内容,
        //通过student1List传递下来的载体是student1,那么value也只能student1,同样的BinaryOperator用的也是student1的属性
        //ps,这里同样接受自定义的compare function
        Map<Integer, Student> studentMap3 = studentList.stream().collect(Collectors.toMap(Student::getId, Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(Student::getLocalDate))));
        //自定义的compare function
        Map<Integer, Student> student1Map4 = studentList.stream().collect(Collectors.toMap(Student::getId, Function.identity(),
                (s1, s2) -> {
                    if (((Student) s1).getLocalDate().compareTo(((Student) s2).getLocalDate()) < -1) {
                        return s2;
                    } else {
                        return s1;
                    }
                }));
        System.out.println("debug");
        //如果不想要默认的hashMap和ConcurrentHashMap,则使用第四个参数
        //ps:在Collectors的重载方法中,没有Key-value-type的参数结构
        Map<Integer, Student> studentMap5 = studentList.stream().collect(Collectors.toMap(Student::getId, Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(Student::getLocalDate)), LinkedHashMap::new));
    }

    /**
     * 聚合规约部分,包含字符串拼接(joining),counting,分组,分区等方法
     * 前提是获得对应的List/Set集合
     */
    public void testCollectors2(List<Student> studentList, List<Integer> integerList) {
        //joining方法
        String string1 = studentList.stream().map(Student::getName).collect(Collectors.joining());
        //带分隔符的参数
        String string2 = studentList.stream().map(Student::getName).collect(Collectors.joining("--"));
        //带分隔符+前后缀,ps这里的参数也是一样固定的
        String string3 = studentList.stream().map(Student::getName).collect(Collectors.joining("--", "[", "]"));
        //普通的stream返回的count为long, 使用Collectors返回的是包装类,一般统计使用Stream的即可,后者更消耗资源
        //同理stream的max ,min 和Collectors的minBy maxBy是一样的适用场景
        long count = studentList.stream().map(Student::getId).count();
        Long count2 = studentList.stream().map(Student::getId).collect(Collectors.counting());
        Optional<Integer> maxValue = integerList.stream().max(Integer::compareTo);
        Optional<Integer> maxValue2 = integerList.stream().collect(Collectors.maxBy(Integer::compareTo));
        //对于聚合函数,Collectors提供了一个统一的聚合方法,返回count,sum,min,max,average的结果
        //summingInt,summarizingLong,summarizingDouble三个方法分别用于Int,long,double数据的求总结果
        //求总结果为IntSummaryStatistics实现类,包含, getCount(),getSum(),getMin(),getAverage(), getMax() 五种结果
        IntSummaryStatistics result = integerList.stream().collect(Collectors.summarizingInt(Integer::valueOf));
        //规约部分,包含groupBy partitionBy方法,其中groupBy可以自定义分组
        //partitionBy因为底层使用了Predicate(只有两种结果,true/false),所以partitionBy的数据只有两组
        //规约方法在分组好之后,需要定义下游的收集器
        Map<String, List<Integer>> groupResultList =
                Stream.of(-1, -2, -3, 0, 1, 2, 3).collect(Collectors.groupingBy(this::checkInteger));
        //自定义收集器
        Map<String, Set<Integer>> groupResultSet =
                Stream.of(-1, -2, -3, 0, 1, 2, 3).collect(Collectors.groupingBy(this::checkInteger,
                        Collectors.toSet()));
        //自定义结果的Map容器和下游收集器
        Map<String, Set<Integer>> groupResultLinkMapAndSet =
                Stream.of(-1, -2, -3, 0, 1, 2, 3).collect(Collectors.groupingBy(this::checkInteger,
                        LinkedHashMap::new, Collectors.toSet()));
        //partitionBy,结果不想groupingBy那样结果的map有自定义的key,partitioningBy的结果为<Boolean,Collection>
        Map<Boolean, Set<Integer>> partitionResut =
                Stream.of(-1, -2, -3, 0, 1, 2, 3).collect(Collectors.partitioningBy(integer -> integer == 0,
                        Collectors.toSet()));
        System.out.println("debug");
    }

    private String checkInteger(Integer integer) {
        if (integer < 0) {
            return "小于";
        } else if (integer == 0) {
            return "等于";
        } else {
            return "大于";
        }
    }
}
