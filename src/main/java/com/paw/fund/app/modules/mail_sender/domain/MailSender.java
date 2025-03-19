package com.paw.fund.app.modules.mail_sender.domain;

import lombok.Builder;
import lombok.With;

@Builder
public record MailSender(
        String from,
        String to,
        @With String subject,
        @With String body,
        String content,
        Boolean isHTMLSupport
) {
    public MailSender configForAccountVerification(String lastName) {
        String subject = "[PAWFUND] - Mã xác nhận tài khoản";
        String body = "<html><body><h2>Xác nhận tài khoản của bạn</h2><p>Chào bạn, %s</p><p>Mã xác nhận của bạn là:</p><h1 style='color: #007bff;'> %s </h1><p>Mã này có hiệu lực trong 15 phút.</p><p>Nếu bạn không yêu cầu, vui lòng bỏ qua email này.</p><br><p>Trân trọng,<br><strong>PawFund</strong></p></body></html>".formatted(lastName, content());

        return this.withSubject(subject).withBody(body);
    }
}
