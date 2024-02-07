package zxf.java.vavr;

import io.vavr.*;
import io.vavr.collection.Seq;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import lombok.Data;

import static io.vavr.API.*;
import static org.hamcrest.Matchers.is;

public class VavrTests {
    public static void main(String[] args) {
        testTuples();
        testValues();
        testFunctions();
        testCollections();
        testMatches();
    }

    private static void testTuples() {
    }

    private static void testValues() {
        //Try
        Try<Integer> divide = Try.of(() -> 1 / 0);
        System.out.println(divide.getOrElse(-1));
        divide.onFailure((ex) -> ex.printStackTrace());

        //Validation
        Validation<String, String> nameValidation = Validation.valid("Davis");
        Validation<String, Integer> ageValidation = Validation.valid(40);
        Validation<String, Integer> ageInValidation = Validation.invalid("Bad age");

        Validation<Seq<String>, Person> personValidation = Validation.combine(nameValidation, ageValidation).ap(Person::new);
        if (personValidation.isValid()) {
            System.out.println(personValidation.get());
        }

        Validation<Seq<String>, Person> personValidation1 = Validation.combine(nameValidation, ageInValidation).ap(Person::new);
        if (!personValidation1.isValid()) {
            System.out.println(personValidation1.getError());
        }
    }

    private static void testFunctions() {
        Function3<Integer, Integer, Integer, Integer> function3 = Function3.of(VavrTests::sum);
        System.out.println(function3.apply(1, 2, 3));
        System.out.println(function3.andThen(VavrTests::square).apply(1, 2, 3));
        System.out.println(function3.tupled().apply(Tuple.of(1, 2, 3)));
        System.out.println(function3.tupled().andThen(VavrTests::square).apply(Tuple.of(1, 2, 3)));

        Function1<Integer, Integer> function1 = function3.apply(2, 3);
        System.out.println(function1.apply(4));

        Function3<Integer, Integer, Integer, Integer> memoizedFunction3 = function3.memoized();
        memoizedFunction3.apply(3, 4, 5);
        memoizedFunction3.apply(3, 4, 5);
        memoizedFunction3.apply(5, 4, 5);
        memoizedFunction3.curried().apply(5).apply(4).apply(5);
    }

    private static void testCollections() {
    }

    private static void testMatches() {
        Match.Case<Integer, String>[] cases = new Match.Case[]{Case($(is(1)), "A"), Case($(is(2)), "B"), Case($(is(3)), "C"), Case($(), "Z")};

        String word = Match(1).of(cases);
        System.out.println(word);

        String word3 = Match(3).of(cases);
        System.out.println(word3);

        String word4 = Match(8).of(cases);
        System.out.println(word4);

        Object obj = 234;
        Number plusOne = Match(obj).of(Case($(Predicates.instanceOf(Integer.class)), i -> i + 1), Case($(Predicates.instanceOf(Double.class)), d -> d + 1), Case($(), o -> {
            throw new NumberFormatException();
        }));

        Try<Tuple2<String, Integer>> successTry = Try.success(new Tuple2<>("a", 1));
        Try<Tuple2<String, Integer>> failureTry = Try.failure(new RuntimeException("failure try"));

        Match.Case<Try<Tuple2<String, Integer>>, Object>[] cases2 = new Match.Case[]{Case(Patterns.$Success(Patterns.$Tuple2($("a"), $())), tuple2 -> tuple2), Case(Patterns.$Failure($(Predicates.instanceOf(Exception.class))), error -> error)};
        System.out.println(Match(successTry).of(cases2));
        System.out.println(Match(failureTry).of(cases2));


        System.out.println("######");
        Match.Case<Try<Tuple2<String, Integer>>, Object>[] cases1 = new Match.Case[]{Case(Patterns.$Success($()), tuple2 -> tuple2), Case(Patterns.$Failure($()), error -> error)};
        System.out.println(Match(successTry).of(cases1));
        System.out.println(Match(failureTry).of(cases1));
    }

    private static Integer sum(Integer a, Integer b, Integer c) {
        System.out.println("sum(" + a + "," + b + "," + c + ")");
        return a + b + c;
    }

    private static Integer square(Integer a) {
        System.out.println("square(" + a + ")");
        return a * a;
    }

    @Data
    @AllArgsConstructor
    public static class Person {
        private String name;
        private Integer age;
    }
}
