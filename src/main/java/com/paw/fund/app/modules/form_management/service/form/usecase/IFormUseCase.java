package com.paw.fund.app.modules.form_management.service.form.usecase;

import com.paw.fund.app.modules.form_management.domain.form.Form;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormDetail;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormFilter;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormId;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormUpdate;
import org.springframework.data.domain.Page;

public interface IFormUseCase {
    Form createForm(Form form);

    Form getFormDetail(FormDetail FormDetail);

    Form updateForm(FormUpdate formUpdate);

    Page<Form> getFormList(FormFilter filter);

    void deleteForm(FormId formId);
}
