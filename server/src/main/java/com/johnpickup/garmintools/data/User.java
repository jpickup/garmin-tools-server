package com.johnpickup.garmintools.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
public class User {
    @Id
    public String id;

    public String firstName;
    public String lastName;
}
