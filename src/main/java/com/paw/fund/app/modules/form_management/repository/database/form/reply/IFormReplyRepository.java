package com.paw.fund.app.modules.form_management.repository.database.form.reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFormReplyRepository extends JpaRepository<FormReplyEntity, Long> {

}
