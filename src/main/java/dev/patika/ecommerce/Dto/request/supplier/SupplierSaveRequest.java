package dev.patika.ecommerce.Dto.request.supplier;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierSaveRequest {
    @NotNull
    private String companyName;

    private String contactName;
    private String address;

    @Email
    private String contactMail;
}
