package org.example;

import java.util.*;

public class Atm {

    private TreeMap<Integer, Integer> atmMap;

    public Atm(int fifty, int twenty) {
        this.atmMap = new TreeMap<>(Comparator.reverseOrder());
        atmMap.put(50, fifty);
        atmMap.put(20, twenty);
    }

    public Withdrawl withdrawMoney(int requestAmount) {
        Withdrawl withdraw ;
        Withdrawl withdrawOutput = null;
        TreeMap<Integer, Integer> tempAtmMap = new TreeMap<>(atmMap);

        for (int index = 0; index < atmMap.keySet().size(); index++) {
            withdraw = new Withdrawl(requestAmount);
            withdrawOutput = withdrawMoneyFromIndex(requestAmount, index, withdraw);
            if (withdrawOutput.checkNotesMatchRequestAmount()) {
                return withdrawOutput;
            }
        }

        atmMap  = new TreeMap<>(tempAtmMap);
        List<Integer> keyList = new ArrayList<>(tempAtmMap.keySet());
        for (int index = 0; index < keyList.size(); index++) {
            withdraw = new Withdrawl(requestAmount);
            withdrawOutput = withdrawMoneyFromIndex(requestAmount, index, withdraw);
            if (!withdrawOutput.checkNotesMatchRequestAmount()) {
                withdrawOutput.remove(keyList.get(index));
                addNote(keyList.get(index));
                withdrawOutput = withdrawMoneyFromIndex(withdraw.amountRemaining(), index + 1, withdrawOutput);
            }
            if (withdrawOutput.checkNotesMatchRequestAmount()) {
                return withdrawOutput;
            }
        }
        atmMap  = new TreeMap<>(tempAtmMap);
        return withdrawOutput;
    }

    private Withdrawl withdrawMoneyFromIndex(int requestAmount, int index, Withdrawl notesToWithdraw) {
        int withdrawlSum = 0;
        List<Integer> keyList = new ArrayList<>(atmMap.keySet());
        for (int i = index; i < keyList.size(); i++) {
            Integer note = keyList.get(i);
            Integer notesRemaining = atmMap.get(note);
            if (requestAmount == note && notesRemaining > 0) {
                removeNote(note);
                notesToWithdraw.add(note);
                return notesToWithdraw;
            }
            if (requestAmount < note || notesRemaining < 1) {
                continue;
            }
            while (requestAmount >= note && notesRemaining > 0) {
                requestAmount = requestAmount - note;
                removeNote(note);
                notesToWithdraw.add(note);
                withdrawlSum = withdrawlSum + note;
                notesRemaining--;
            }
            if (requestAmount == 0) {
                return notesToWithdraw;
            }
        }
        return notesToWithdraw;
    }

    public boolean checkWithdrawRequest(int requestAmount) {
        Withdrawl withdrawl = withdrawMoney(requestAmount);
        return withdrawl.checkNotesMatchRequestAmount();
    }

    public int checkAmount() {
        int amount = 0;
        for (Map.Entry<Integer, Integer> entry : atmMap.entrySet()) {
            Integer note = entry.getKey();
            Integer notesRemaining = entry.getValue();
            while (notesRemaining > 0) {
                amount = amount + note;
                notesRemaining--;
            }
        }
        return amount;
    }

    private void removeNote(int note) {
        if (atmMap.get(note) == 0) {
            atmMap.put(note, 0);
        } else
            atmMap.merge(note, -1, Integer::sum);
    }

    private void addNote(int note) {
        atmMap.merge(note, 1, Integer::sum);
    }

    public void showBalance() {
        System.out.println("Atm Balance : { $50 : x" + atmMap.get(50) + "}, { $20 : x" + atmMap.get(20) + "}");
    }
}
