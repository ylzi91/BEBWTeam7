package yurilenzi.entities;

public enum Capienza {

    AUTOBUS(45),
    TRAM(60);

    public final int value;

    Capienza(int val) {
        this.value = val;
    }
}
