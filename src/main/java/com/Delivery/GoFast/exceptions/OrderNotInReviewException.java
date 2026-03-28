package com.Delivery.GoFast.exceptions;

public class OrderNotInReviewException extends RuntimeException {

    public OrderNotInReviewException() {
        super("Order is not in review");
    }

    public OrderNotInReviewException(String message) {
        super(message);
    }
}
