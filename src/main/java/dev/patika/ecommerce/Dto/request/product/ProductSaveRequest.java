package dev.patika.ecommerce.Dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaveRequest {
    private String name;
    private double prc;
    private int stock;
    private int supplierId; //Burada private Supplier supplier yerine int kullandık çünkü; biz burada sadece id yi öğrenmek istiyoruz. Tüm Supplier bilgisini kullanıcıya göstermek istemiyoruz.
    private int categoryId; //Burada private Category category yerine int kullandık çünkü; biz burada sadece id yi öğrenmek istiyoruz. Tüm Category bilgisini kullanıcıya göstermek istemiyoruz.
}
