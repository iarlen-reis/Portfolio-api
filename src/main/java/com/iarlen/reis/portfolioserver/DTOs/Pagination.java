package com.iarlen.reis.portfolioserver.DTOs;

public record Pagination(long total, long pages, boolean hasPrevious, boolean hasNext) {
}
