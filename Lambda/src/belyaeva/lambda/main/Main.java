package belyaeva.lambda.main;

import belyaeva.lambda.task.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();

        persons.add(new Person("John", 35));
        persons.add(new Person("Alice", 20));
        persons.add(new Person("Jane", 12));
        persons.add(new Person("John", 15));
        persons.add(new Person("Ken", 31));
        persons.add(new Person("Molly", 35));

        /*А) получить список уникальных имен*/
        List<String> uniqueNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        System.out.println("uniqueNames = " + uniqueNames);

        /*Б) вывести список уникальных имен в формате: Имена: Иван, Сергей, Петр.*/
        String uniqueNamesString = uniqueNames.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println("uniqueNamesString = " + uniqueNamesString);

        /*В) получить список людей младше 18, посчитать для них средний возраст*/
        List<String> personsUnder18 = persons.stream()
                .filter(p -> p.getAge() < 18)
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.println("personsUnder18 = " + personsUnder18);

        OptionalDouble averageAge = persons.stream()
                .filter(p -> p.getAge() < 18)
                .mapToInt(Person::getAge)
                .average();

        averageAge.ifPresent(System.out::println);

        /*Г) при помощи группировки получить Map, в котором ключи – имена, а значения – средний возраст*/
        Map<String, List<Person>> personsByName = persons.stream()
                .collect(Collectors.groupingBy(Person::getName));

        personsByName.forEach((name, age) -> System.out.printf("Name %s: %s%n", name, age.stream()
                .mapToInt(Person::getAge)
                .average().orElse(0)));

        /*Д) получить людей, возраст которых от 20 до 45, вывести в консоль их имена
             в порядке убывания возраста*/
        List<String> personsFrom25To45 = persons.stream().filter(p -> p.getAge() >= 25 && p.getAge() <= 45)
                .sorted((age1, age2) -> age2.getAge() - age1.getAge())
                .map(Person::getName)
                .collect(Collectors.toList());

        personsFrom25To45.forEach(System.out::println);

        /*Создать бесконечный поток корней чисел. С консоли прочитать число – сколько
          элементов нужно вычислить, затем – распечатать эти элементы*/
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число:");
        int n = scanner.nextInt();
        DoubleStream squareRoots = DoubleStream.iterate(1, x -> x + 1).limit(n).map(Math::sqrt);

        squareRoots.forEach(System.out::println);
    }
}
