// Adrián Navarro Gabino

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    static List<Student> filterStudents(
            List<Student> srcList, Predicate<Student> predicate)
    {
        return srcList.stream()
                .filter(s -> predicate.test(s)).collect(Collectors.toList());
    }

    public static void main(String[] args)
    {
        List<Student> students = new ArrayList<Student>();

        students.add(new Student("Adrian", 25, new ArrayList<String>(
                List.of("Subject1",
                "Subject2",
                "Subject3"))));
        students.add(new Student("Alfredo Peter", 18, new ArrayList<String>(
                List.of("Subject2",
                        "Subject4"))));
        students.add(new Student("Paula", 30, new ArrayList<String>(
                List.of("Subject1",
                        "Subject3"))));
        students.add(new Student("Peter", 27, new ArrayList<String>(
                List.of("Subject1",
                        "Programming",
                        "Subject4"))));
        students.add(new Student("Laura", 19, new ArrayList<String>(
                List.of("Subject3"))));
        students.add(new Student("Monica", 23, new ArrayList<String>(
                List.of("Subject1",
                        "Programming",
                        "Subject4"))));
        students.add(new Student("Ramon", 24, new ArrayList<String>(
                List.of("Programming",
                        "Subject4"))));
        students.add(new Student("Andres", 45, new ArrayList<String>(
                List.of("Subject1",
                        "Subject2",
                        "Subject3",
                        "Subject4"))));

        List<Student> olderThan20 = filterStudents(students,
                s -> s.getAge() > 20);
        List<Student> enrolledInProgramming = filterStudents(students,
                s -> s.getSubjects().stream()
                        .anyMatch(sub -> sub.equals("Programming")));
        List<Student> peters = filterStudents(
                students, s -> s.getName().contains("Peter"));

        System.out.println("Students that are older than 20:");
        olderThan20.stream().forEach(s -> System.out.println(s));
        System.out.println();
        System.out.println(
                "Students that are inscribed in the \"Programming\" subject");
        enrolledInProgramming.stream().forEach(s -> System.out.println(s));
        System.out.println();
        System.out.println("Students whose name contains \"Peter\"");
        peters.stream().forEach(s -> System.out.println(s));
    }
}
