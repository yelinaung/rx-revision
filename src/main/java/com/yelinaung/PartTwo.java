package com.yelinaung;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;

import static com.yelinaung.ShitUtils.printShit;

/**
 * * RxJava Part Two
 *
 * <a href="http://blog.danlew.net/2014/09/22/grokking-rxjava-part-2">
 * Grokking RxJava, Part 3: Reactive with Benefits
 * </a>
 * Created by yelinaung on 24/12/16.
 */
public class PartTwo {
  public static void main(String[] args) {

    // from is disambiguated into fromArray, fromIterable, fromFuture
    Observable.fromArray("first", "url1", "url2", "url3")
        .subscribe(new Consumer<String>() {
          @Override public void accept(String s) throws Exception {
            printShit(s); // this is actually looping and printing
          }
    });

    query("second url1 url2 url3")
        .subscribe(new Consumer<List<String>>() {
          @Override public void accept(List<String> strings) throws Exception {
            Observable.fromIterable(strings) // this loop the shit!
              .subscribe(new Consumer<String>() {
                @Override public void accept(String s) throws Exception {
                  printShit(s);
                }
              });
      }
    });

    // a better way, he said
    query("better way hello world")
        .flatMap(new Function<List<String>, ObservableSource<String>>() {
          @Override public ObservableSource<String> apply(List<String> strings) throws Exception {
            return Observable.fromIterable(strings);
          }
        })
        .subscribe(new Consumer<String>() {
          @Override public void accept(String s) throws Exception {
            printShit(s);
          }
        });

    // lambda style
    query("better way in lambda")
        .flatMap(urls -> Observable.fromIterable(urls))
        .subscribe(url -> printShit(url));

    // it gets even better
    query("hello it gets even better")
        .flatMap(new Function<List<String>, ObservableSource<String>>() {
          @Override public ObservableSource<String> apply(List<String> urls) throws Exception {
            return Observable.fromIterable(urls);
          }
        })
        .flatMap(new Function<String, ObservableSource<String>>() {
          @Override public ObservableSource<String> apply(String url) throws Exception {
            return getTitle(url);
          }
        })
        .subscribe(new Consumer<String>() {
          @Override public void accept(String s) throws Exception {
            printShit(s);
          }
        });

    // it gets even better in lambda style
    // MAGIC!
    query("it even gets better in lambda style")
        .flatMap(urls -> Observable.fromIterable(urls))
        .flatMap(url -> getTitle(url))
        .subscribe(title -> printShit(title));

    // MORE OPERATORS
    query("more operators")
        .flatMap(urls -> Observable.fromIterable(urls))
        .flatMap(url -> getTitle(url))
        // filter emits the same item it received,
        // but only if it passes the boolean check.
        .filter(title -> title != null)
        .subscribe(title -> printShit(title));

    query("show only five")
        .flatMap(urls -> Observable.fromIterable(urls))
        .flatMap(url -> getTitle(url))
        .filter(title -> title != null)
        .take(5)
        .doOnNext(title -> saveTitle(title))
        .subscribe(title -> printShit(title));

  }

  private static Observable<List<String>> query(String text) {
    List<String> listOfStrings = new ArrayList<>();
    for (String s : text.split(" ")) {
      listOfStrings.add(String.format("url %s", s));
    }
    return Observable.just(listOfStrings);
  }

  private static Observable<String> getTitle(String url) {
    return Observable.just("Title - " + url);
  }

  private static Observable<String> saveTitle(String title) {
    printShit("Saving " + title);
    return Observable.just(title);
  }

}
