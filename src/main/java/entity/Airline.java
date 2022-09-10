package entity;

public enum Airline {
    EMIRATES_AIRLINE("EK"),
    AMERICAN_AIRLINE("AA"),
    TURKISH_AIRLINE("TK"),
    QATAR_AIRWAYS("QR"),
    AZAL("J2"),
    LUFTHANSA("LH"),
    AIR_CHINA("CA"),
    BRITISH_AIRWAYS("BA"),
    UKRAINE_INTERNATIONAL_AIRLINE("PS");

    private final String designator;

    Airline(String designator) {
        this.designator = designator;
    }

    public String getDesignator() {
        return designator;
    }
}
