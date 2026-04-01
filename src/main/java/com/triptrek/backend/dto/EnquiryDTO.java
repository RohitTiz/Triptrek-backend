package com.triptrek.backend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EnquiryDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Please enter a valid 10-digit Indian mobile number")
    private String phone;

    @Size(max = 500, message = "Address cannot exceed 500 characters")
    private String address;

    @Size(max = 1000, message = "Message cannot exceed 1000 characters")
    private String message;

    @NotBlank(message = "Item name is required")
    private String itemName;

    @NotBlank(message = "Item type is required")
    @Pattern(regexp = "^(Destination|Package)$", message = "Item type must be either 'Destination' or 'Package'")
    private String itemType;
}