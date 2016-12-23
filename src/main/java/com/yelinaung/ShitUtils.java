package com.yelinaung;

import java.util.List;

/**
 * Shitty Utils
 *
 * Created by (shitty) yelinaung on 24/12/16.
 */
class ShitUtils {
  static void printShit(String s) {
    System.out.println(s);
  }

  static void printShit(List<String> s) {
    s.forEach(System.out::println);
  }
}
