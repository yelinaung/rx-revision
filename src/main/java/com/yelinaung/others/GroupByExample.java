package com.yelinaung.others;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.flowables.GroupedFlowable;
import io.reactivex.subscribers.DisposableSubscriber;
import java.util.ArrayList;
import java.util.List;

import static com.yelinaung.ShitUtils.printShit;

/**
 * GroupByExample example
 *
 * Created by yelinaung on 24/12/16.
 */
public class GroupByExample {
  public static void main(String[] args) {
    Observable.range(0, 21)
        .groupBy(i -> i % 2 == 0 ? "even" : "odd")
        .subscribe(groupObservable -> groupObservable.subscribe(i -> {
          printShit("key=" + groupObservable.getKey() + " " + "value=" + i);
        }));

    List<Integer> evenList = new ArrayList<>();
    List<Integer> oddList = new ArrayList<>();
    Flowable.range(0, 21).groupBy(item -> item % 2 == 0 ? "EVEN" : "ODD")
        .subscribeWith(new DisposableSubscriber<GroupedFlowable<String, Integer>>() {
      @Override public void onNext(GroupedFlowable<String, Integer> groupedFlowable) {
        groupedFlowable.subscribe(i -> {
          if (groupedFlowable.getKey().equals("EVEN")) {
            evenList.add(i);
          } else {
            oddList.add(i);
          }
        });
      }

      @Override public void onError(Throwable t) {
      }

      @Override public void onComplete() {
        printShit("on complete");
        evenList.forEach(integer -> printShit("even " + integer));
        oddList.forEach(integer -> printShit("odd " + integer));
      }
    });
  }
}
