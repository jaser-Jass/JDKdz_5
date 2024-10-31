package org.example;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {
    public static void main(String[] args) {
        final int NUM_PHILOSOPHERS = 5;
        Lock[] forks = new Lock[NUM_PHILOSOPHERS];

        // Создаем вилки
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new ReentrantLock();
        }

        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];

        // Создаем философов
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            Lock leftFork = forks[i];
            Lock rightFork = forks[(i + 1) % NUM_PHILOSOPHERS]; // Правую вилку берет следующий философ
            philosophers[i] = new Philosopher(i, leftFork, rightFork);
        }

        // Запускаем философов
        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }

        // Ждем завершения всех философов
        for (Philosopher philosopher : philosophers) {
            try {
                philosopher.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Все философы закончили.");
    }
}
