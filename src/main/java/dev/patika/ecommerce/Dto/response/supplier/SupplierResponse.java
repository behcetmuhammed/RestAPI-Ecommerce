package dev.patika.ecommerce.Dto.response.supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierResponse {
    // Aşağıdakiler sadece kullanıcıya vermek istediğim bilgiler (id, companyName)
    // Diğer bilgileri vermek istemiyoruz (contactName, address, contactMail)
    private int id;
    private String companyName;
}
