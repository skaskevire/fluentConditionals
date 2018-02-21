package com.epam.fluentConditionals;
import static com.epam.fluentConditionals.FluentConditionals.*;

public class IfElse {

    public static void main(String[] args) {

        when(TestHelper.somethingIsTrue())
                .then(TestHelper::printBar)
                .orElse(TestHelper::printFoo);
        //'Bar' printed to console

        when(TestHelper::somethingIsTrue)
                .then(TestHelper::printBar)
                .orElse(TestHelper::printFoo);
        //'Bar' printed to console


        when(!TestHelper.somethingIsTrue())
                .then(TestHelper::printBar)
        		.orElse(doNothing());
        //nothing printed to console
    }
}