package dementev.hamitov.arthritisapp.models;

public enum ArthritisType {
    CHRONIC("Хронический"),
    ACUTE("Острый"),
    SUBACUTE("Подострый");

    private String name;

    ArthritisType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
