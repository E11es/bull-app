package com.example.application.service.implementation;

import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;

@Service
public class NumberService {

    public String generateNumber(int size) {
        LinkedHashSet<Integer> randomNumbersSet = new LinkedHashSet<>();
        int random;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            do {
                random = (int) (Math.random() * 10);
            } while (randomNumbersSet.contains(random));
            randomNumbersSet.add(random);
        }
        for (int i : randomNumbersSet) {
            stringBuilder.append(i);
        }
        return stringBuilder.toString();
    }


}
