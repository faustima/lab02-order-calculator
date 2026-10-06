package by.bseu.pp.lab02;

/**
 * Utility methods for calculating and validating simple order data.
 *
 * <p>Implement every TODO method without changing its signature.</p>
 */
public class OrderCalculator {
    public static double calculateSubtotal(double[] prices, int[] quantities) {
        if (prices == null || quantities == null) {
            throw new IllegalArgumentException("Массивы не могут быть null");
        }
        if (prices.length != quantities.length) {
            throw new IllegalArgumentException("Длины массивов должны совпадать");
        }
        if (prices.length == 0) {
            throw new IllegalArgumentException("Массивы не могут быть пустыми");
        }

        double subtotal = 0.0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < 0) {
                throw new IllegalArgumentException("prices не может быть отрицательной");
            }
            if (quantities[i] <= 0) {
                throw new IllegalArgumentException("quantities должно быть больше 0");
            }
            subtotal += prices[i] * quantities[i];
        }
        return subtotal;
    }



    public static double determineDiscountRate(int previousOrdersCount) {
        if (previousOrdersCount < 0) {
            throw new IllegalArgumentException("previousOrdersCount должно быть >=0");
        }
        if (previousOrdersCount < 20) {
            return 0.00;
        } else if (previousOrdersCount < 100) {
            return 0.07;
        } else {
            return 0.15;
        }
    }

    public static double applyDiscount(double subtotal, double discountRate) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("subtotal не может быть отрицательной");
        }
        if (discountRate < 0.0 || discountRate > 1.0) {
            throw new IllegalArgumentException("discountRate должна быть в диапазоне [0.0, 1.0]");
        }
        return subtotal * (1.0 - discountRate);
    }

    public static double addTax(double amount, double taxRate) {
        if (amount<0) {
            throw new IllegalArgumentException("amount не может быть отрицательной");
        }
        if (taxRate < 0.0 || taxRate > 1.0) {
            throw new IllegalArgumentException("taxRate должна быть в диапазоне [0, 1]");
        }
        return amount * (1.0 + taxRate);

    }

    public static double calculateTotal(
            double[] prices,
            int[] quantities,
            int previousOrdersCount,
            double taxRate) {
        double subtotal = calculateSubtotal(prices, quantities);
        double discountRate = determineDiscountRate(previousOrdersCount);
        double discountedAmount = applyDiscount(subtotal, discountRate);
        return addTax(discountedAmount, taxRate);
    }

    public static boolean isWithinCreditLimit(double total, double creditLimit) {
        if (total < 0 || creditLimit < 0) {
            throw new IllegalArgumentException("total и creditLimit не могут быть отрицательными");
        }
        return total <= creditLimit;
    }

    public static int countOrdersWithinLimit(double[] totals, double creditLimit) {
        if (totals == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }
        if (creditLimit < 0) {
            throw new IllegalArgumentException("creditLimit не может быть отрицательным");
        }

        int count = 0;
        for (double total : totals) {
            if (total < 0) {
                throw new IllegalArgumentException("Сумма заказа не может быть отрицательной");
            }
            if (total <= creditLimit) {
                count++;
            }
        }
        return count;

    }
}
