package ua.vholovetskyi.exchangerate.domain;

public enum BankType {
    P_24("Privat24"),
    MINFIN("Minifin"),
    MONOBANK("Monobank");

    private final String name;

    BankType(String name) {
        this.name = name;
    }
}
