package com.hzj.java;

import sun.applet.Main;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : hzj
 * @version : 1.0
 * @Project : JavaSenior
 * @Package : com.hzj.java
 * @ClassName : .java
 * @createTime : 2021/7/24 16:14
 * @Email : 1458422066@qq.com
 * @Description :
 */


class Clerk{

    private int productNum = 0;

    public synchronized void  produceProduct() {
        if(productNum < 20){
            productNum++;
            System.out.println(Thread.currentThread().getName()+":正在生产第"+productNum+"个产品");

            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void consumeProduct() {
        if(productNum > 0){
            System.out.println(Thread.currentThread().getName()+":正在消费第"+productNum+"个产品");
            productNum--;
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer extends Thread{
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName()+"开始生产产品.......");
        while (true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceProduct();
        }

    }
}

class Consumer extends Thread{
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName()+"开始消费产品.......");
        while (true){
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumeProduct();
        }
    }
}

public class ProducTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer producer = new Producer(clerk);
        producer.setName("生产者");


        Consumer consumer = new Consumer(clerk);
        consumer.setName("消费者");

        Consumer consumer2 = new Consumer(clerk);
        consumer2.setName("消费者2");

        producer.start();
        consumer.start();
        consumer2.start();
    }


}
