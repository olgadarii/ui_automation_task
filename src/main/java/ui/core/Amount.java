package ui.core;

public enum Amount {
    $50,
    $100,
    $200;

    public static double getAmountAsDouble(Amount amount){
        return Double.parseDouble(amount.toString()
                .replace(",", "").substring(1));
    }
}
