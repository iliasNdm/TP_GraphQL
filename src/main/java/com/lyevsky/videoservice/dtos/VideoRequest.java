package com.lyevsky.videoservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class VideoRequest {

    private String name;

    private String url;

    private String description;

    /** Format attendu : dd/MM/yyyy (converti en java.util.Date par VideoMapper). */
    private String datePublication;

    private CreatorRequest creator;
}
