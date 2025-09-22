package com.yxchai.javaassessment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto extends ResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 4127057410099210130L;

    private Long customerId;

    @NotBlank(message = "Customer name is required!", groups = {AddGroup.class})
    @Size(max = 100, message = "Customer name can have at most 100 characters !")
    private String name;

    @Email(message = "Customer email is not in valid format !")
    @Size(max = 100, message = "Customer email can have at most 100 length !")
    private String email;

    public interface AddGroup {}
}
