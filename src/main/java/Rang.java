public enum Rang {
    KID("для детей"),
    ADULT("для взрослых"),
    SENIOR("для старших");

    private final String desc;

    Rang(String roomDesc) {
        desc = roomDesc;
    }

    @Override
    public String toString() {
        return desc;
    }
}
