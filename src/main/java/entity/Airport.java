package entity;

public enum Airport {
    MADRID("MAD"),
    BAKU("GYD"),
    ISTANBUL("IST"),
    KYIV("IEV"),
    FRANKFURT("FRA"),
    BARCELONA("BAR"),
    LOS_ANGELES("LAX"),
    NEW_YORK("JFK"),
    PARIS("CDG"),
    ROMA("FCO"),
    TALLINN("TLL");

    private final String code;

    Airport(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
