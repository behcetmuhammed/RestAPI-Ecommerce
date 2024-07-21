package dev.patika.ecommerce.Business.concretes;

import dev.patika.ecommerce.Business.abstracts.IProductService;
import dev.patika.ecommerce.Core.exception.NotFoundException;
import dev.patika.ecommerce.Core.utilies.Msg;
import dev.patika.ecommerce.Dao.ProductRepo;
import dev.patika.ecommerce.entities.Product;
import dev.patika.ecommerce.entities.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductManager implements IProductService {
    private final ProductRepo productRepo;

    public ProductManager(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    //Kaydetme
    @Override
    public Product save(Product product) {
        return this.productRepo.save(product);
    }

    //Getirme
    @Override
    public Product get(int id) {
        return this.productRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    //Sayfalama
    @Override
    public Page<Product> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.productRepo.findAll(pageable);
    }

    //Güncelleme
    @Override
    public Product update(Product product) {
        this.get(product.getId());
        return this.productRepo.save(product);
    }

    //Silme
    @Override
    public Boolean delete(int id) {
        Product product = this.get(id);
        this.productRepo.delete(product);
        return true;
    }
}
