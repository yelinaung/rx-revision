package com.yelinaung;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

import static com.yelinaung.ShitUtils.printShit;

/**
 * Part Three
 *
 * <a href="http://blog.danlew.net/2014/09/30/grokking-rxjava-part-3">
 * Grokking RxJava, Part 3: Reactive with Benefits
 * </a>
 * Created by yelinaung on 24/12/16.
 */
public class PartThree {
  public static void main(String[] args) {

    // Error handling
    Observable.just("Hello, world!")
        .map(new Function<String, String>() {
          @Override public String apply(String s) throws Exception {
            return potentialException(s);
          }
        })
        .map(new Function<String, String>() {
          @Override public String apply(String s) throws Exception {
            return anotherPotentialException(s);
          }
        })
        //.flatMap(new Function<String, ObservableSource<?>>() {
        //  @Override public ObservableSource<?> apply(String s) throws Exception {
        //    return flatMapException(s);
        //  }
        //})
    .subscribe(new DisposableObserver<String>() {
      @Override public void onNext(String s) {
        printShit(s);
      }

      @Override public void onError(Throwable e) {
        printShit("Ouch!");
      }

      @Override public void onComplete() {
        printShit("Complete!");
      }
    });

    Disposable disposable = Observable.just("Hello subscriptions")
        .subscribe(new Consumer<String>() {
      @Override public void accept(String s) throws Exception {
        printShit(s);
      }
    });
    disposable.dispose();
    printShit("disposed ? " + disposable.isDisposed());

  }

  private static String potentialException(String s) {
    return "potentialException - " + s;
  }

  private static String anotherPotentialException(String s) {
    return "anotherPotentialException - " + s;
  }

  private static Observable<String> flatMapException(String s) {
    return Observable.just("flatMapException - " + s);
  }
}
