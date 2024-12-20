package me.nettee.basket.exception;

import me.nettee.common.exeption.ErrorCode;
import org.springframework.http.HttpStatus;

public enum BasketCommandErrorCode implements ErrorCode {
    BASKET_NOT_FOUND("존재하지 않는 장바구니입니다", HttpStatus.NOT_FOUND),
    BASKET_FORBIDDEN("권한이 없습니다.", HttpStatus.FORBIDDEN),
    DEFAULT("조작 오류", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;

    BasketCommandErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String defaultMessage() {
        return message;
    }

    @Override
    public HttpStatus defaultHttpStatus() {
        return status;
    }

    @Override
    public RuntimeException defaultException() {
        return new BasketCommandException(this);
    }

    @Override
    public RuntimeException defaultException(Throwable cause) {
        return new BasketCommandException(this, cause);
    }
}
