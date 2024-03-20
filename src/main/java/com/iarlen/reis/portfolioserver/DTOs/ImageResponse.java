package com.iarlen.reis.portfolioserver.DTOs;

import java.util.Map;

public record ImageResponse(String id, String name, String url) {
    public ImageResponse(Map<String, Object> resource) {
        this(
                (String) resource.get("asset_id"),
                (String ) resource.get("filename"),
                (String) resource.get("url")
        );
    }
}
