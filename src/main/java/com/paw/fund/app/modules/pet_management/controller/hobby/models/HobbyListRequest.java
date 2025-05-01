package com.paw.fund.app.modules.pet_management.controller.hobby.models;

import lombok.Builder;

import java.util.List;

@Builder
public record HobbyListRequest(List<HobbyRequest> list) {
}
