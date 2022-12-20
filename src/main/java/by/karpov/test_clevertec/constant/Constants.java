package by.karpov.test_clevertec.constant;

import java.math.BigDecimal;

public class Constants {
    public static final String TITLE = """
             
            \n CASH RECEIPT
             supermarket
             tel : 53443643 \n
             """;
    public static final String TOTAL = "\nTOTAL                                               $";
    public static final String PERCENT_DISCOUNT = "discount card number %7s -- %s percent discount";
    public static final String TITLE_TABLE = "\n  qty  |   description           |   price   |  total     |";
    public static final String DATE_ORDER = "date order ";
    public static final String TIME_ORDER = "  time order  ";
    public static final String NEW_LINE = "\n";
    public static final String PRODUCT_ELEMENT_FORMAT = " \n %3d   |%25s|  $%8s|  $%8s | ";
    public static final String SEPARATE_LINE = "-----------------------------------------------------------";
    public static final BigDecimal DIVISOR = new BigDecimal(100);
    public static final String DISCOUNT_FOR_STOCK_PRODUCT = "0.9";
    public static final int QTY_FOR_GET_DISCOUNT = 5;
    public static final BigDecimal BIG_DECIMAL_DISCOUNT_FOR_STOCK_PRODUCT = new BigDecimal(DISCOUNT_FOR_STOCK_PRODUCT);
    public static final String EMPTY_CARD = "empty";
    public static final String ORDER_ID = "order_id_";
    public static final String SPACE = "_";
    public static final String FILE_SAVE_AS = "File save as - ";
    public static final String TXT = ".txt";
    public static final BigDecimal BIG_DECIMAL = new BigDecimal(0);
    public static final String DISCOUNT_CARD_DOES_NOT_EXIST = "Discount card doesn't exist";


}
