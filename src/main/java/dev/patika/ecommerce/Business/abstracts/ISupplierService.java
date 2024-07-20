package dev.patika.ecommerce.Business.abstracts;

import dev.patika.ecommerce.entities.Supplier;
import org.springframework.data.domain.Page;

public interface ISupplierService {
    Supplier save(Supplier supplier);

    Supplier get(int id);

    Page<Supplier> cursor(int page, int pageSize);

    Supplier update(Supplier supplier);

    Boolean delete(int id);
}
