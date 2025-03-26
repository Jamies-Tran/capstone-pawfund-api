package com.paw.fund.app.modules.category_management.controller.role;

import com.paw.fund.app.modules.category_management.controller.role.models.IRoleCategoryModelMapper;
import com.paw.fund.app.modules.category_management.controller.role.models.RoleCategoryResponse;
import com.paw.fund.app.modules.category_management.domain.usecase.RoleSearch;
import com.paw.fund.app.modules.category_management.service.role.usecase.IRoleCategoryUseCase;
import com.paw.fund.utils.response.ListResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleCategoryV1PubController implements IRoleCategoryV1PubAPI {
    @NonNull
    IRoleCategoryUseCase useCase;

    @NonNull
    IRoleCategoryModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ListResponse<RoleCategoryResponse> getRoleList(String search) {
        List<RoleCategoryResponse> responses = useCase.getRoleList(RoleSearch.of(search))
                .stream()
                .map(modelMapper::toResponse)
                .toList();

        return ListResponse.success(responses, HttpStatus.OK, API_VERSION);
    }
}
