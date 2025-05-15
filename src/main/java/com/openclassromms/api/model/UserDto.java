package com.openclassromms.api.model;

import java.time.format.DateTimeFormatter;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String created_at;
    private String updated_at;

    public UserDto(Long id, String name, String email, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public static UserDto fromUser(User user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt().format(formatter),
                user.getUpdatedAt().format(formatter)
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
