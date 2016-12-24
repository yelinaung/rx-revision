package com.yelinaung;

import java.util.ArrayList;
import java.util.List;

/**
 * Shitty Utils
 *
 * Created by (shitty) yelinaung on 24/12/16.
 */
public class ShitUtils {
  public static void printShit(String s) {
    System.out.println(s);
  }

  public static void printShit(Integer i) {
    System.out.println(String.valueOf(i));
  }

  static void printShit(List<String> s) {
    s.forEach(System.out::println);
  }

  public static List<Integer> generateFibonaciNumbers() {

    ArrayList<Integer> a = new ArrayList<Integer>();

    a.add(0);// enter the 1st elemnt of the list
    a.add(1);// 2nd elemnt
    int currIndex = 1;

    //--- i set the limit as first 50 items
    while (currIndex < 10) {
      a.add(((Integer) a.get(currIndex)) + ((Integer) a.get(currIndex - 1)));
      currIndex++;
    }

    return a;
  }

  public static List<String> generateAlphabet() {

    ArrayList<String> a = new ArrayList<>();

    a.add("a");
    a.add("b");
    a.add("c");
    a.add("d");
    a.add("e");
    a.add("f");
    a.add("g");
    a.add("h");
    a.add("i");
    a.add("j");

    return a;
  }

  public static List<String> generateWords() {

    ArrayList<String> a = new ArrayList<>();

    a.add("airplane");
    a.add("beattle");
    a.add("cat");
    a.add("dog");
    a.add("energy");
    a.add("fire");

    return a;
  }

  public static List<String> generateAlphabetDuplicado() {

    ArrayList<String> a = new ArrayList<>();
    a.addAll(generateAlphabet());
    a.addAll(generateAlphabet());
    a.addAll(generateAlphabet());
    return a;
  }
}
