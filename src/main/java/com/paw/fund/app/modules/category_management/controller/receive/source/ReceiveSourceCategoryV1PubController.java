package com.paw.fund.app.modules.category_management.controller.receive.source;

import com.paw.fund.app.modules.category_management.controller.receive.source.models.IReceiveSourceCategoryModelMapper;
import com.paw.fund.app.modules.category_management.controller.receive.source.models.ReceiveSourceCategoryResponse;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.receive.source.usecase.IReceiveSourceUseCase;
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
public class ReceiveSourceCategoryV1PubController implements IReceiveSourceCategoryV1PubAPI {
    @NonNull
    IReceiveSourceUseCase useCase;

    @NonNull
    IReceiveSourceCategoryModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ListResponse<ReceiveSourceCategoryResponse> getReceiveSourceCategoryList(String search) {
        List<ReceiveSourceCategoryResponse> responses = useCase.getReceiveSourceCategoryList(CategorySearch.of(search))
                .stream()
                .map(modelMapper::toResponse)
                .toList();

        return ListResponse.success(responses, HttpStatus.OK, API_VERSION);
    }
}
