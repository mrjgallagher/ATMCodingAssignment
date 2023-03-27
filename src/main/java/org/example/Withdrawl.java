package org.example;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Withdrawl {

    private final TreeMap<Integer, Integer> withrawlAmountMap;
    private final int requestAmount;

    public Withdrawl(int requestAmount) {
        this.withrawlAmountMap = new TreeMap<>(Comparator.reverseOrder());
        this.requestAmount =requestAmount;
        withrawlAmountMap.put(50, 0);
        withrawlAmountMap.put(20, 0);
    }


    public void add(int note) {
        withrawlAmountMap.merge(note, 1, Integer::sum);
    }

    public void remove(int note) {
        if (withrawlAmountMap.get(note) == 0) {
            withrawlAmountMap.put(note, 0);
        } else
            withrawlAmountMap.merge(note, -1, Integer::sum);
    }

    public int countNotesWithdrawn(){
        int amount = 0;
        for (Map.Entry<Integer, Integer> entry : withrawlAmountMap.entrySet()) {
            Integer note = entry.getKey();
            Integer notesRemaining = entry.getValue();
            while (notesRemaining > 0) {
                amount = amount +note;
                notesRemaining--;
            }
        }
        return amount ;
    }

    public boolean checkNotesMatchRequestAmount(){
        int amount = 0;
        for (Map.Entry<Integer, Integer> entry : withrawlAmountMap.entrySet()) {
            Integer note = entry.getKey();
            Integer notesRemaining = entry.getValue();
            while (notesRemaining > 0) {
                amount = amount +note;
                notesRemaining--;
            }
        }
        return amount == requestAmount;
    }

    public int amountRemaining(){
        return requestAmount - countNotesWithdrawn();
    }

    public void showBalance() {
        System.out.println("Amount Withdrawn : { $50 : x" + withrawlAmountMap.get(50) + "}, { $20 : x" + withrawlAmountMap.get(20) + "}");
    }
}
