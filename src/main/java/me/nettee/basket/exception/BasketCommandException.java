package me.nettee.basket.exception;

import me.nettee.common.exeption.CustomException;
import me.nettee.common.exeption.ErrorCode;

public class BasketCommandException extends CustomException {
    public BasketCommandException(ErrorCode errorCode) {
        super(errorCode);
    }
    public BasketCommandException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
