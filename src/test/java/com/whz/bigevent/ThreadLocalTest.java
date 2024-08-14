package com.whz.bigevent;

import org.junit.jupiter.api.Test;

/**
 * @author MyPC
 * @description
 * @since 2024/8/14 21:40
 */

public class ThreadLocalTest {
    @Test
    public void testThreadLocalSetAndGet() {
        // 提供1个ThreadLocal对象
        ThreadLocal threadLocal = new ThreadLocal();
        // 开启2个线程
        new Thread(() -> {
            threadLocal.set("萧炎");
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
        }, "蓝色").start();
        new Thread(() -> {
            threadLocal.set("药尘");
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
        }, "绿色").start();
    }
}
