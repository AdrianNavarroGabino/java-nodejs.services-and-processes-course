// Adrián Navarro Gabino

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.lang.CharSequence.compare;

public class Main {
    public static TreeSet<String> getAllSubjects(List<Student> list)
    {
        Set<String> subjects =  list.stream()
                .map(s -> s.getSubjects())
                .reduce(new ArrayList<String>(), (a, b) -> {
                    List<String> subList = new ArrayList<>();
                    subList.addAll(a);
                    subList.addAll(b);
                    return subList;
                }).stream()
                    .collect(Collectors.toSet());

        return new TreeSet<>(subjects);
    }

    public static List<String> getOldestNames(List<Student>  list)
    {
        Comparator<Student> oldestComparator = (a,b) -> Integer.compare(b.getAge(), a.getAge());

        return list.stream()
                .sorted(oldestComparator)
                .limit(3)
                .map(a -> a.getName()).collect(Collectors.toList());
    }

    static List<Student> filterStudents(
            List<Student> srcList, Predicate<Student> predicate)
    {
        List<Student> auxList = new ArrayList<>();

        for(Student st: srcList) {
            if(predicate.test(st)) {
                auxList.add(st);
            }
        }

        return auxList;
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

        List<Student> olderThan20 = filterStudents(students, st -> st.getAge() >= 20);
        System.out.println("Students that are older than 20:");
        for(Student s: olderThan20)
            System.out.println(s);
        System.out.println();

        List<Student> enrolledInProgramming = filterStudents(students,
                st -> st.getSubjects().contains("Programming"));
        System.out.println(
                "Students that are inscribed in the \"Programming\" subject");
        for(Student s: enrolledInProgramming)
            System.out.println(s);
        System.out.println();

        List<Student> peters = filterStudents(students,
                st -> st.getName().contains("Peter"));
        System.out.println("Students whose name contains \"Peter\":");
        for(Student s: peters)
            System.out.println(s);
        System.out.println();

        List<String> oldestStudents = getOldestNames(students);
        oldestStudents.stream().forEach(System.out::println);
        System.out.println();

        getAllSubjects(students).stream().forEach(System.out::println);
    }
}
