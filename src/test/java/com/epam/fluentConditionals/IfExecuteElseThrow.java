package com.epam.fluentConditionals;

import static com.epam.fluentConditionals.FluentConditionals.*;

//Task 2
public class IfExecuteElseThrow {

  public static void main(String[] args) {

      when(TestHelper::somethingIsTrue)
              .then(TestHelper::printBar)
      .orElseThrowE(new RuntimeException());
      //'Bar' printed to console

      when(TestHelper::somethingIsTrue)
              .then(TestHelper::printBar)
      .orElseThrow(RuntimeException::new);
      //'Bar' printed to console

      when(TestHelper::somethingIsTrue)
              .then(TestHelper::printBar)
      .orElseThrow(TestHelper::createException);
      //'Bar' printed to console

      when(!TestHelper.somethingIsTrue())
              .then(TestHelper::printFoo)
              .orElseThrow(TestHelper::createException);
      //exception thrown
  }
}