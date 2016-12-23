package com.yelinaung;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static com.yelinaung.ShitUtils.printShit;

/**
 * RxJava Part One
 *
 * <a href="http://blog.danlew.net/2014/09/15/grokking-rxjava-part-1">
 * Grokking RxJava, Part 3: Reactive with Benefits
 * </a>
 * Created by yelinaung on 23/12/16.
 */
public class PartOne {
  public static void main(String[] args) {

    // Normal way
    // There is no more Action(N) in RxJava 2
    // https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0#actions
    Observable<String> myObservable = Observable.just("Hello, world!");
    Consumer<String> consumer = new Consumer<String>() {
      public void accept(String s) throws Exception {
        printShit(s);
      }
    };
    myObservable.subscribe(consumer);

    // Shorter subscriber
    Observable.just("Hello World 2")
        .subscribe(new Consumer<String>() {
      public void accept(String s) throws Exception {
        printShit(s);
      }
    });

    // Lambda
    Observable.just("Hello Lambda").subscribe(System.out::println);

    // Operators
    Observable.just("Hello Operators").map(new Function<String, String>() {
      @Override public String apply(String s) throws Exception {
        return s + " - YLA";
      }
    }).subscribe(s -> printShit(s));

    Observable.just("Hello Operators with Lambda")
        .map(s -> s + " - YLA")
        .subscribe(System.out::println);

    // More map
    // Map does not have to emit items of the same type as the source
    // Observable
    Observable.just("More map").map(new Function<String, Integer>() {
      @Override public Integer apply(String s) throws Exception {
        return s.hashCode();
      }
    }).subscribe(i -> System.out.println(Integer.toString(i)));

    // More and more map
    Observable.just("Hello more map!")
        .map(s -> s.hashCode()) // convert to integer here
        .map(integer -> Integer.toString(integer)) // convert back to string
        .subscribe(s -> printShit(s)); // print it out!

    // Let's do more map
    Observable.just("Three maps yo!")
        .map(s -> s + " - YLA")
        .map(s -> s.hashCode())
        .map(integer -> Integer.toString(integer))
        .subscribe(s -> printShit(s));

  }
}
