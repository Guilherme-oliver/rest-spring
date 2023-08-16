package com.oliveira.erudio.math;

import com.oliveira.erudio.converters.NumberConverter;
import com.oliveira.erudio.exceptions.UnsupportedMathOperationException;

import java.util.concurrent.atomic.AtomicLong;

public class SimpleMath {

    public Double sum(Double numberOne, Double numberTwo) {
        return numberOne + numberTwo;
    }

    public Double subtract(Double numberOne, Double numberTwo) {
        return numberOne - numberTwo;
    }

    public Double multiplication(Double numberOne, Double numberTwo) {

        return numberOne * numberTwo;
    }

    public Double division(Double numberOne, Double numberTwo) {
        return numberOne / numberTwo;
    }

    public Double mean(Double numberOne, Double numberTwo) {
        return (numberOne + numberTwo) /2;
    }


    public Double squareRoot(Double number) {
        return Math.sqrt(number);
    }
}