package com.example.dubv2.dto;

import com.example.dubv2.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @JsonProperty
    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @JsonProperty
    @NotNull
    @Size(min = 3, max = 100)
    private String password;
    @JsonProperty
    @NotNull
    @Size(min = 3, max = 50)
    private String nickname;
    @JsonProperty
    private Set<AuthorityDto> authorityDtoSet;

    public static UserDto from(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .authorityDtoSet(user.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}