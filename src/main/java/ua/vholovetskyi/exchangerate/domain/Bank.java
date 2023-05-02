package ua.vholovetskyi.exchangerate.domain;

import java.util.Arrays;

public enum Bank {
    PRIVATE_24, MINFIN, MONOBANK;

    public Bank parseString(String bank) {
        return Arrays.stream(values())
                .filter(b -> b.name().equalsIgnoreCase(bank))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("This type of bank is not supported: " + bank));
    }
}
