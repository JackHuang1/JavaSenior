package com.hzj.java;

import java.lang.reflect.Executable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : hzj
 * @version : 1.0
 * @Project : JavaSenior
 * @Package : com.hzj.java
 * @ClassName : .java
 * @createTime : 2021/7/24 23:48
 * @Email : 1458422066@qq.com
 * @Description : 创建线程的方式四：使用线程池
 * 好处：
 * 1、提高响应速度（减少了创建新线程的时间）
 * 2、降低资源消耗（重复利用线程池中线程，不需要每次都创建）
 * 3、 便于线程管理
 *    corePoolSize：核心池的大小
 *    maximumPoolSize：最大线程数
 *    keepAliveTime：线程没有任务时最多保持多长时间后会终止
 */


class numThread1 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if(i % 2 !=0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}
class numThread2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}

public class ThreadPool {
    public static void main(String[] args) {
        //1、提供指定线程数量的线程池
        ExecutorService Service = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) Service;

        //设置线程池的属性
        //System.out.println(Service.getClass());
        service1.setCorePoolSize(15);
        //2、执行指定的线程的操作，需要提供实现Runnable接口或Callable接口实现类的对象
        Service.execute(new numThread2());//适合适用于Runnable
        Service.execute(new numThread1());//适合适用于Runnable
        //Service.submit(); //适合使用与Callable

        //关闭连接池
        Service.shutdown();
    }
}
