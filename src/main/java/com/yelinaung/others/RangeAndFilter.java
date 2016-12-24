package com.yelinaung.others;

import com.yelinaung.ShitUtils;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * Testing Range and filter
 * Created by yelinaung on 24/12/16.
 */
public class RangeAndFilter {
  public static void main(String[] args) {
    // Range and filter
    Flowable<Integer> rangeAndFilter = Flowable.range(0, 11).filter(integer -> (integer % 2 == 0));

    Consumer<Integer> printIntConsumer = ShitUtils::printShit;

    rangeAndFilter.subscribe(printIntConsumer);
  }
}
