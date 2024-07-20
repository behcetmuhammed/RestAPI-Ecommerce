package dev.patika.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //Entity
@Table(name = "suppliers") //Bu entityi SQL'de 'categories' tablosuyla eşleştir.
@Data //Oto olarak getter ve setter ları yazar
@AllArgsConstructor //Dolu bir constructor oluşturur.
@NoArgsConstructor //Boş bir constructor oluşturur.
public class Supplier {

    @Id //bu sınıfın birincil anahtarını belirtir
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private int id;

    @Column(name = "supplier_name")
    private String companyName; //şirket adı

    @Column(name = "supplier_contect")
    private String contactName; //yetkili  kişi

    @Column(name = "supplier_address")
    private String address; //adres

    @Column(name = "supplier_mail")
    private String contactMail; //mail
}
