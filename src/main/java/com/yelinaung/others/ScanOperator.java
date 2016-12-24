package com.yelinaung.others;

import com.yelinaung.ShitUtils;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableMaybeObserver;

import static com.yelinaung.ShitUtils.printShit;

/**
 * Scan operator
 *
 * Created by yelinaung on 24/12/16.
 */
public class ScanOperator {
  public static void main(String[] args) {
    Observable<String> observable = Observable.fromIterable(ShitUtils.generateAlphabet());
    observable.scan((s, s2) -> s + s2)
        .lastElement()
        .subscribeWith(new DisposableMaybeObserver<String>() {
          @Override public void onSuccess(String s) {
            printShit(s);
          }

          @Override public void onError(Throwable e) {

          }

          @Override public void onComplete() {
          }
        });
  }
}
