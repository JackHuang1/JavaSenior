package com.hzj.java;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : hzj
 * @version : 1.0
 * @Project : JavaSenior
 * @Package : com.hzj.java
 * @ClassName : .java
 * @createTime : 2021/7/24 23:23
 * @Email : 1458422066@qq.com
 * @Description :创建Callable 接口  ---JDK 5.0 新增
 *
 * 如何理解实现Callable接口创建多线程比实现Runnable接口创建多线程更强大？
 * 1、call()可以有返回值
 * 2、call()可以抛出异常，被外面的操作捕获，获取异常的信息
 * 3、Callable是支持泛型的
 *
 *
 *
 */
//1创建一个重写的Callable 实现类
class NumThread implements Callable{
    //2、实现call方法，将此线程需要执行的操作声明在call()中
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 1; i < 100 ; i++) {
            if(i % 2 == 0){
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}

public class ThreadNew {
    public static void main(String[] args) {
        //3、创建Callable接口实现类的对象
        NumThread numThread = new NumThread();
        //4、将此Callable接口是实现类的对象作为传递到FutureTask构造器中，创建FutureTask对象
        FutureTask futureTask = new FutureTask(numThread);
        //5、将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()
        new Thread(futureTask).start();
        try {
            //获取Callable中call方法的返回值
            //get 方法返回值即为futuretask 构造器参数callable 实现类重写的call()的返回值
            Object sum = futureTask.get();
            System.out.println("总和为：" + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
