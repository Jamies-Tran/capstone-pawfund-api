package com.paw.fund.app.modules.form_management.service.form.usecase;

import com.paw.fund.app.modules.form_management.domain.form.Form;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormId;

public interface IFormUseCase {
    Form createForm(Form form);

    Form getFormDetail(FormId formId);
}
