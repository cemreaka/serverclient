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
public class SumThread implements Runnable {

    private int x, y, z;
    private SharedLocation sl;

    public SumThread(int x, int y, int z, SharedLocation sl) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.sl = sl;
    }

    @Override
    public void run() {
        sl.add(sum());
    }

    public int sum() {
        return (this.x + this.y + this.z);
    }

}
