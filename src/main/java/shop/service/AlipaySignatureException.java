package shop.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "签名无效")
public class AlipaySignatureException extends ServiceException {

    public AlipaySignatureException(Exception cause) {
        super(cause);
    }

    public AlipaySignatureException() {
    }

}
