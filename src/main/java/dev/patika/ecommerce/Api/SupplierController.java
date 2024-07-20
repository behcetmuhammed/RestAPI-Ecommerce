package dev.patika.ecommerce.Api;

import dev.patika.ecommerce.Business.abstracts.ISupplierService;
import dev.patika.ecommerce.Core.config.modelMapper.IModelMapperService;
import dev.patika.ecommerce.Core.result.Result;
import dev.patika.ecommerce.Core.result.ResultData;
import dev.patika.ecommerce.Core.utilies.ResultHelper;
import dev.patika.ecommerce.Dto.request.CursorResponse;
import dev.patika.ecommerce.Dto.request.category.CategorySaveRequest;
import dev.patika.ecommerce.Dto.request.category.CategoryUpdateRequest;
import dev.patika.ecommerce.Dto.request.supplier.SupplierSaveRequest;
import dev.patika.ecommerce.Dto.request.supplier.SupplierUpdateRequest;
import dev.patika.ecommerce.Dto.response.category.CategoryResponse;
import dev.patika.ecommerce.Dto.response.supplier.SupplierResponse;
import dev.patika.ecommerce.entities.Category;
import dev.patika.ecommerce.entities.Supplier;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/suppliers")
public class SupplierController {
    private final ISupplierService supplierService;
    private final IModelMapperService modelMapper;

    public SupplierController(ISupplierService supplierService, IModelMapperService modelMapper) {
        this.supplierService = supplierService;
        this.modelMapper = modelMapper;
    }

    ///////////////
    //SAVE (Yeni bir kullanıcı ekleme - CREATE)
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) //oluşturma (POST) statüsü
    public ResultData<SupplierResponse> save(@Valid @RequestBody SupplierSaveRequest supplierSaveRequest) {
        Supplier saveSupplier = this.modelMapper.forRequest().map(supplierSaveRequest, Supplier.class);
        this.supplierService.save(saveSupplier);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveSupplier, SupplierResponse.class));
    }

    //PUT ( bilgilerini güncelleme - UPDATE)
    @PutMapping()
    @ResponseStatus(HttpStatus.OK) //oluşturma (POST) statüsü
    public ResultData<SupplierResponse> update(@Valid @RequestBody SupplierUpdateRequest supplierUpdateRequest) {
        Supplier updateSupplier = this.modelMapper.forRequest().map(supplierUpdateRequest, Supplier.class);
        this.supplierService.update(updateSupplier);
        return ResultHelper.succes(this.modelMapper.forResponse().map(updateSupplier, SupplierResponse.class));
    }

    //DELETE (Kullanıcıyı silmek için - Delete)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.supplierService.delete(id);
        return ResultHelper.ok();
    }

    //GET (Kullanıcı listesini çekme - READ)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<SupplierResponse> get(@PathVariable("id") int id) {
        Supplier supplier = this.supplierService.get(id);
        SupplierResponse supplierResponse = this.modelMapper.forResponse().map(supplier, SupplierResponse.class);
        return ResultHelper.succes(supplierResponse);
    }

    //GET (cursor: gösterge/imleçler)
    @GetMapping() //?page=1&pageSize=10
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<SupplierResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Supplier> supplierPage = this.supplierService.cursor(page, pageSize);
        Page<SupplierResponse> supplierResponsePage = supplierPage
                .map(supplier -> this.modelMapper.forResponse().map(supplier, SupplierResponse.class));

        return ResultHelper.cursor(supplierResponsePage);
    }
}
