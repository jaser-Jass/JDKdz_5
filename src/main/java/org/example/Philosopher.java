package org.example;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher extends Thread{
    private final int id;
    private final Lock leftFork;
    private final Lock rightFork;

    public Philosopher(int id, Lock leftFork, Lock rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void think() {
        System.out.println("Философ " + id + " размышляет.");
        // Симуляция размышления
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void eat() {
        System.out.println("Философ " + id + " ест.");
        // Симуляция еды
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) { // Каждый философ ест три раза
            think();
            pickForks();
            eat();
            putForks();
        }
    }

    private void pickForks() {
        // Берем вилки в нужном порядке
        leftFork.lock(); // Захватываем левую вилку
        rightFork.lock(); // Захватываем правую вилку
    }

    private void putForks() {
        // Освобождаем вилки
        rightFork.unlock(); // Ставим правую вилку
        leftFork.unlock();  // Ставим левую вилку
    }
}
