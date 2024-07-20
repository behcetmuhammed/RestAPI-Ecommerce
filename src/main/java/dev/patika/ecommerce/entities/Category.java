package dev.patika.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //Entity
@Table(name = "categories") //Bu entityi SQL'de 'categories' tablosuyla eşleştir.
@Data //Oto olarak getter ve setter ları yazar
@AllArgsConstructor //Dolu bir constructor oluşturur.
@NoArgsConstructor //Boş bir constructor oluşturur.
public class Category {
    @Id //bu sınıfın birincil anahtarını belirtir
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;

    @NotNull
    @Column(name = "category_name")
    private String name;

}
