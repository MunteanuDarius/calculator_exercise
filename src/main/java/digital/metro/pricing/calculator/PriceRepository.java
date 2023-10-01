package digital.metro.pricing.calculator;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * A dummy implementation for testing purposes. In production, we would get real prices from a database.
 */
@Repository
public class PriceRepository {

    private Map<String, BigDecimal> prices = new HashMap<>();
    private Random random = new Random();
    private final String CUSTOMER_ID_1 = "customer-1";
    private final String CUSTOMER_ID_2 = "customer-2";
    private final BigDecimal CUSTOMER_DISCOUNT_1 = new BigDecimal("0.90");
    private final BigDecimal CUSTOMER_DISCOUNT_2 = new BigDecimal("0.85");

    public BigDecimal getPriceByArticleId(String articleId) {
        return prices.computeIfAbsent(articleId,
                key -> BigDecimal.valueOf(0.5d + random.nextDouble() * 29.50d).setScale(2, RoundingMode.HALF_UP));
    }

    public BigDecimal getPriceByArticleIdAndCustomerId(String articleId, String customerId) {
        switch(customerId) {
            case CUSTOMER_ID_1:
                return getPriceByArticleId(articleId).multiply(CUSTOMER_DISCOUNT_1).setScale(2, RoundingMode.HALF_UP);
            case CUSTOMER_ID_2:
                return getPriceByArticleId(articleId).multiply(CUSTOMER_DISCOUNT_2).setScale(2, RoundingMode.HALF_UP);
            default:
                return getPriceByArticleId(articleId);
        }
    }
}
