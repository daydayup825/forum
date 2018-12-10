package com.tensquare;

/**
 * @author: fanbopeng
 * @Date: 2018/11/25 19:44
 * @Description:
 */
public class Test {



    public static void main(String[] args) {

        Object o1=new Object();
        Object o2=new Object();


        o1=null;
        o2=null;
       // o1，o2 最后都被赋值为 null，也就是说之前 o1，o2 所引用的对象都无法被访问。
        // 但是由于两个对象互相引用对方，
        // 所以它们的引用计数器都不为 0，所以垃圾收集器无法回收它们。



    }

}
