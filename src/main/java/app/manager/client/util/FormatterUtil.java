package app.manager.client.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.beans.BeanProperty;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

@Service
public class FormatterUtil {
    public static String formatCurrency(Number amount) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        DecimalFormat decimalFormat = (DecimalFormat) currencyFormat;
        decimalFormat.applyPattern("###,##0.000 đ/kg");
        return currencyFormat.format(amount) ;
    }
}

