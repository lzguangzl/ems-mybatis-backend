package com.ems.playground;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaStream {
    public static void main(String[] args) {


        Person p1 = new Person("1", "alligator", "alligator@gmail.com");
        Person p2 = new Person("2", "bear", "bear@gmail.com");
        Person p3 = new Person("3", "cat", "cat@gmail.com");
        Person p4 = new Person("4", "dragonfly", "dragonfly@gmail.com");

        Person p5 = new Person("1", "elephant", "elephant@gmail.com");
        Person p6 = new Person("2", "falcon", "falcon@gmail.com");
        Person p7 = new Person("3", "gorilla", "gorilla@gmail.com");
        Person p8 = new Person("4", "hippo", "dragonfly@gmail.com;hippo@gmail.com");

        Person p9 = new Person("4", "iguana", "hippo@gmail.com");
        Person p10 = new Person("3", "jaguar", "jaguar@gmail.com;jaguar@gmail.com");
        Person p11 = new Person("5", "koala", "koala@gmail.com");
        Person p12 = new Person("6", "lion", "lion@gmail.com;lion@gmail.com");

        List<Person> pList1 = Arrays.asList(p1, p2, p3, p4);
        List<Person> pList2 = Arrays.asList(p5, p6, p7, p8);
        List<Person> pList3 = Arrays.asList(p9, p10, p11, p12);

        Set<String> allItems = new HashSet<>();
        pList3.forEach(person -> {
            Set<String> pEmail = Arrays.stream(person.getEmail().split(";")).collect(Collectors.toSet());
            person.setEmail(String.join(";", pEmail));
        });
        pList3.stream().forEach(System.out::println);

        Map<String, Person> pMap1 = Stream.of(pList1, pList2, pList3)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Person::getId, Function.identity(), (e1, e2) -> {
                            Person pTemp = new Person();
                            pTemp.setId(e1.getId());
                            pTemp.setName(e1.getName().concat(";").concat(e2.getName()));

                            Set<String> emailSet1 = Arrays.stream(e1.getEmail().split(";")).collect(Collectors.toSet());
                            Set<String> emailSet2 = Arrays.stream(e2.getEmail().split(";")).collect(Collectors.toSet());
                            Set<String> finalEmailSet = new HashSet<>();
                            for (String email1 : emailSet1) {
                                for (String email2 : emailSet2) {
                                    if (!email1.equalsIgnoreCase(email2)) {
                                        finalEmailSet.add(email2);
                                    }
                                }
                                finalEmailSet.add(email1);
                            }
                            pTemp.setEmail(String.join(";", finalEmailSet));

                            return pTemp;
                        }
                ));

        pMap1.entrySet().stream().forEach(e -> System.out.println(e.getKey() + " : " + e.getValue().toString()));

    }

    private static class Person implements Serializable {

        private String id;
        private String name;
        private String email;

        public Person() {
        }

        public Person(String id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }
}
