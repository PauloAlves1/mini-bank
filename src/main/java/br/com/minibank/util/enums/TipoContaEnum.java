package br.com.minibank.util.enums;

public enum TipoContaEnum {

    FREE("FREE"),
    BASIC("BASIC"),
    PREMIUM("PREMIUM");

    private String value;

    private TipoContaEnum(String value) {this.value = value; }

    public String getValue() { return value;}
}
