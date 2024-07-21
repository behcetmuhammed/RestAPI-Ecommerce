package dev.patika.ecommerce.Business.concretes;

import dev.patika.ecommerce.Business.abstracts.ISupplierService;
import dev.patika.ecommerce.Core.exception.NotFoundException;
import dev.patika.ecommerce.Core.utilies.Msg;
import dev.patika.ecommerce.Dao.SupplierRepo;
import dev.patika.ecommerce.entities.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SupplierManager implements ISupplierService {

    private final SupplierRepo supplierRepo;

    public SupplierManager(SupplierRepo supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

    //Kaydetme
    @Override
    public Supplier save(Supplier supplier) {
        return this.supplierRepo.save(supplier);
    }

    //Getirme
    @Override
    public Supplier get(int id) {
        return this.supplierRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    //Sayfalama
    @Override
    public Page<Supplier> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.supplierRepo.findAll(pageable);
    }

    //Güncelleme
    @Override
    public Supplier update(Supplier supplier) {
        this.get(supplier.getId());
        return this.supplierRepo.save(supplier);
    }

    //Silme
    @Override
    public Boolean delete(int id) {
        Supplier supplier = this.get(id);
        this.supplierRepo.delete(supplier);
        return true;
    }
}
