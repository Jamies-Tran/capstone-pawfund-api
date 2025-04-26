package com.paw.fund.app.modules.mail_management.domain;

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
    public MailSender prepareForAccountVerification(String lastName) {
        String subject = "[PAWFUND] - Mã xác nhận tài khoản";
        String body = "<html><body><h2>Xác nhận tài khoản của bạn</h2><p>Chào bạn, %s</p><p>Mã xác nhận của bạn là:</p><h1 style='color: #007bff;'> %s </h1><p>Mã này có hiệu lực trong 15 phút.</p><p>Nếu bạn không yêu cầu, vui lòng bỏ qua email này.</p><br><p>Trân trọng,<br><strong>PawFund</strong></p></body></html>".formatted(lastName, content());

        return this.withSubject(subject).withBody(body);
    }

    public MailSender prepareForEmailVerification(String lastName) {
        String subject = "[PAWFUND] - Mã xác nhận thay đổi email mới";
        String body = "<html><body><h2>Xác nhận email của bạn</h2><p>Chào bạn, %s</p><p>Mã xác nhận của bạn là:</p><h1 style='color: #007bff;'> %s </h1><p>Mã này có hiệu lực trong 15 phút.</p><p>Nếu bạn không yêu cầu, vui lòng bỏ qua email này.</p><br><p>Trân trọng,<br><strong>PawFund</strong></p></body></html>".formatted(lastName, content());

        return this.withSubject(subject).withBody(body);
    }

    public MailSender prepareForEmailReceiveShelter(String lastName, String shelterName) {
        String subject = "[PAWFUND] - Thông báo về trung tâm cứu trợ mới";
        String body = """
                <html>
                  <body style="font-family: Arial, sans-serif; color: #333;">
                    <h2>Yêu cầu tạo trung tâm cứu trợ đã được tiếp nhận</h2>
                
                    <p>Chào bạn, <strong>%s</strong></p>
                
                    <p>Chúng tôi đã tiếp nhận yêu cầu tạo trung tâm cứu trợ <strong>%s</strong> của bạn.</p>
                
                    <p>Hồ sơ của bạn đang trong quá trình kiểm duyệt và sẽ được phản hồi trong vòng <strong>24 giờ</strong>.</p>
                
                    <p>Sau khi được duyệt, bạn có thể bắt đầu tiếp nhận các khoản đóng góp, đăng thông tin thú cưng thông qua nền tảng PawFund.</p>
                
                    <h3 style="color: #007bff;">🙏 Cảm ơn bạn vì đã chung tay giúp đỡ cộng đồng!</h3>
                
                    <br>
                    <p>Trân trọng,<br>
                    <strong>Đội ngũ PawFund</strong></p>
                  </body>
                </html>
                """.formatted(lastName, shelterName);
        return this.withSubject(subject).withBody(body);
    }

    public MailSender prepareForEmailApproveShelter(String lastName, String shelterName) {
        String subject = "[PAWFUND] - Thông báo về trung tâm cứu trợ mới";
        String body = """
                <html>
                  <body style="font-family: Arial, sans-serif; color: #333;">
                    <h2>Trung tâm cứu trợ của bạn đã được duyệt</h2>
                   
                    <p>Chào bạn, <strong>%s</strong></p>
                   
                    <p>Chúng tôi rất vui thông báo rằng trung tâm cứu trợ <strong>%s</strong> của bạn đã được kiểm duyệt và chính thức hoạt động trên nền tảng PawFund.</p>
                   
                    <p>Bạn có thể bắt đầu tiếp nhận các khoản đóng góp, đăng thông tin thú cưng, và cập nhật tình hình trung tâm ngay từ bây giờ.</p>
                
                    <h3 style="color: #007bff;">🎉 Chúc bạn thành công trong việc hỗ trợ cộng đồng!</h3>
                   
                    <br>
                    <p>Trân trọng,<br>
                    <strong>Đội ngũ PawFund</strong></p>
                  </body>
                </html>
                """.formatted(lastName, shelterName);
        return this.withSubject(subject).withBody(body);
    }

    public MailSender prepareForEmailRejectShelter(String lastName, String shelterName, String rejectReason) {
        String subject = "[PAWFUND] - Thông báo về trung tâm cứu trợ mới";
        String body = """
                <html>
                         <body style="font-family: Arial, sans-serif; color: #333;">
                           <h2>Yêu cầu tạo trung tâm cứu trợ bị từ chối</h2>
                
                           <p>Chào bạn, <strong>%s</strong></p>
                
                           <p>Chúng tôi rất tiếc phải thông báo rằng yêu cầu tạo trung tâm cứu trợ <strong>%s</strong> của bạn đã bị từ chối sau quá trình kiểm duyệt.</p>
                
                           <p><strong>Lý do từ chối:</strong></p>
                           <blockquote style="border-left: 4px solid #dc3545; padding-left: 10px; color: #dc3545;">
                             %s
                           </blockquote>
                
                           <p>Bạn có thể chỉnh sửa thông tin trung tâm và gửi lại yêu cầu mới nếu cần.</p>
                
                           <h3 style="color: #007bff;">📌 Nếu có bất kỳ thắc mắc nào, đừng ngần ngại liên hệ với chúng tôi.</h3>
                
                           <br>
                           <p>Trân trọng,<br>
                           <strong>Đội ngũ PawFund</strong></p>
                         </body>
                       </html>
                """.formatted(lastName, shelterName, rejectReason);
        return this.withSubject(subject).withBody(body);
    }
}
