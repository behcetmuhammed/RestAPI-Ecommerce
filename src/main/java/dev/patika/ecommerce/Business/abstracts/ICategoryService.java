package dev.patika.ecommerce.Business.abstracts;

import dev.patika.ecommerce.entities.Category;
import org.springframework.data.domain.Page;

public interface ICategoryService {
    Category save(Category category);

    Category get(int id);

    Page<Category> cursor(int page, int pageSize);

    Category update(Category category);

    Boolean delete(int id);


}
