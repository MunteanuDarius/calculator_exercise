package digital.metro.pricing.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class BasketCalculatorController {

    private BasketCalculatorService basketCalculatorService;

    @Autowired
    public BasketCalculatorController(BasketCalculatorService basketCalculatorService) {
        this.basketCalculatorService = basketCalculatorService;
    }

    @PostMapping("/calculator/calculate-basket")
    public BasketCalculationResult calculateBasket(@RequestBody Basket basket) {
        return basketCalculatorService.calculateBasket(basket);
    }

    @GetMapping("/calculator/articles/{articleId}")
    public BigDecimal getArticlePrice(@PathVariable String articleId) {
        return basketCalculatorService.calculateArticle(new BasketEntry(articleId, BigDecimal.ONE), null);
    }

    @GetMapping("/calculator/customers/{customerId}/articles/{articleId}/price")
    public BigDecimal getArticlePriceForCustomer(@PathVariable String articleId, @PathVariable String customerId) {
        return basketCalculatorService.calculateArticle(new BasketEntry(articleId, BigDecimal.ONE), customerId);
    }
}
