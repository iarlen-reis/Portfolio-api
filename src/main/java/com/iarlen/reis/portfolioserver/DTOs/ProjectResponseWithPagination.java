package com.iarlen.reis.portfolioserver.DTOs;

import java.util.List;

public record ProjectResponseWithPagination(Pagination pagination, List<ProjectResponse> projects) {
}
