package com.hzj.java;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : hzj
 * @version : 1.0
 * @Project : JavaSenior
 * @Package : com.hzj.java
 * @ClassName : WindowTest.java
 * @createTime : 2021/7/23 16:24
 * @Email : 1458422066@qq.com
 * @Description :
 */
public class WindowTest {
    public static void main(String[] args) {
        Window1 w = new Window1();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
class Window1 implements Runnable{
    private int ticket = 100;
    Object object = new Object();
    @Override
    public void run() {
        while (true){
            synchronized(object){
                if(ticket > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":售票，票号为："+ticket--);
                }else{
                    break;
                }
            }

        }
    }
}
