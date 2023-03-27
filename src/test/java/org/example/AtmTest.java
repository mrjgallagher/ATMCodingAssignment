package org.example;

import org.junit.Assert;
import org.junit.Test;


public class AtmTest {

    @Test
    public void Test0_get10() {
        Atm atm = new Atm(1, 0);
        Assert.assertFalse(atm.checkWithdrawRequest(10));
    }

    @Test
    public void Test1_get50() {
        Atm atm = new Atm(1, 0);
        Assert.assertTrue(atm.checkWithdrawRequest(50));
    }

    @Test
    public void test2_Withdraw20() {
        Atm atm = new Atm(0, 1);
        Assert.assertTrue(atm.checkWithdrawRequest(20));
    }

    @Test
    public void test3_Withdraw40() {
        Atm atm = new Atm(0, 2);
        Assert.assertTrue(atm.checkWithdrawRequest(40));
    }

    @Test
    public void test4_Withdraw70() {
        Atm atm = new Atm(1, 2);
        Assert.assertTrue(atm.checkWithdrawRequest(70));
    }

    @Test
    public void test5_Withdraw70_v2() {
        Atm atm = new Atm(2, 2);
        Assert.assertTrue(atm.checkWithdrawRequest(70));
    }

    @Test
    public void test6_Withdraw80t() {
        Atm atm = new Atm(2, 4);
        Assert.assertTrue(atm.checkWithdrawRequest(70));
    }

    @Test
    public void test7_Withdraw100() {
        Atm atm = new Atm(2, 4);
        Assert.assertTrue(atm.checkWithdrawRequest(100));
    }

    @Test
    public void test8_Withdraw150() {
        Atm atm = new Atm(3, 4);
        Assert.assertTrue(atm.checkWithdrawRequest(150));
    }

    @Test
    public void test9_Withdraw60() {
        Atm atm = new Atm(0, 4);
        Assert.assertTrue(atm.checkWithdrawRequest(60));
    }

    @Test
    public void test10_Withdraw60_v2() {
        Atm atm = new Atm(1, 4);
        Assert.assertTrue(atm.checkWithdrawRequest(60));
    }

    @Test
    public void test11_Withdraw110() {
        Atm atm = new Atm(2, 4);
        Assert.assertTrue(atm.checkWithdrawRequest(110));
    }

    @Test
    public void test12_Withdraw200() {
        Atm atm = new Atm(2, 5);
        Assert.assertTrue(atm.checkWithdrawRequest(200));
    }

    @Test
    public void test13_Withdraw200() {
        Atm atm = new Atm(3, 8);
        Assert.assertTrue(atm.checkWithdrawRequest(200));
    }

    @Test
    public void test14_Withdraw100_Check_Correct_Atm_Balance_Remaining() {
        Atm atm = new Atm(2, 2);
        Withdrawl result = atm.withdrawMoney(100);
        Assert.assertEquals(100, result.countNotesWithdrawn());
        Assert.assertEquals(atm.checkAmount(), 40);
    }

    @Test
    public void test15_Withdraw50_Check_Correct_Atm_Balance_Remaining() {
        Atm atm = new Atm(1, 0);
        Withdrawl result = atm.withdrawMoney(50);
        Assert.assertEquals(50, result.countNotesWithdrawn());
        Assert.assertEquals(atm.checkAmount(), 0);
    }

    @Test
    public void test16_Withdraw70_Check_Correct_Atm_Balance_Remaining() {
        Atm atm = new Atm(2, 3);
        Withdrawl result = atm.withdrawMoney(70);
        Assert.assertEquals(70, result.countNotesWithdrawn());
        Assert.assertEquals(atm.checkAmount(), 90);
    }

    @Test
    public void test17_Withdraw200_Check_Correct_Atm_Balance_Remaining() {
        Atm atm = new Atm(3, 5);
        Withdrawl result = atm.withdrawMoney(200);
        Assert.assertEquals(200, result.countNotesWithdrawn());
        Assert.assertEquals(atm.checkAmount(), 50);
    }

    @Test
    public void test17_Withdraw300_Check_Correct_Atm_Balance_Remaining() {
        Atm atm = new Atm(3, 5);
        Withdrawl result = atm.withdrawMoney(300);
        Assert.assertEquals(0, result.countNotesWithdrawn());
        Assert.assertEquals(atm.checkAmount(), 250);
    }

}