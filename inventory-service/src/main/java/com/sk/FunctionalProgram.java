package com.sk;

import java.util.function.BiFunction;

public class FunctionalProgram {
    private static BiFunction<Integer, Integer, Integer> sum = (x, y) -> x+y;

    private static BiFunction<Integer, Integer, Integer> divide = (x,y) -> x/y;

    private static Operation multiply = (x,y) -> x*y;

    private static Operation substract = (x,y) -> x-y;

    public static void main(String[] args) {
        FunctionalProgram obj = new FunctionalProgram();
        System.out.println(obj.doOperation(sum, 10, 20));
        System.out.println(obj.doOperation(divide  , 30, 10));
        System.out.println(substract.doOperation(5,4));
    }
    public Integer doOperation(BiFunction function, Integer x, Integer y){
        return (Integer)function.apply(x,y);
    }
}

@FunctionalInterface
interface Operation{
    public Integer doOperation(Integer x, Integer y);
}
