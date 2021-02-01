/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpclientserverapp;

/**
 *
 * @author cemreaka
 */
public class SharedLocation {

    private int[] shared_loc;
    private int count = 0;

    public SharedLocation(int size) {
        shared_loc = new int[size];
    }

    public synchronized void add(int value) {
        int index = this.count;
        this.shared_loc[index] = value;
        System.out.printf("%s performs its task and writes the sum %d\n", Thread.currentThread().getName(), value);
        count++;
    }

    public int totalSum() {
        int totSum = 0;
        for (int i = 0; i < shared_loc.length; i++) {
            totSum += this.shared_loc[i];
        }

        return totSum;
    }

    @Override
    public String toString() {
        String s = "Elements: ";
        for (int i = 0; i < shared_loc.length; i++) {
            s += this.shared_loc[i] + " ";
        }

        return s;
    }

}
