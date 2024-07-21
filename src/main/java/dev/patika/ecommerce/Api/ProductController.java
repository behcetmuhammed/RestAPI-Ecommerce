package dev.patika.ecommerce.Api;

import dev.patika.ecommerce.Business.abstracts.ICategoryService;
import dev.patika.ecommerce.Business.abstracts.IProductService;
import dev.patika.ecommerce.Business.abstracts.ISupplierService;
import dev.patika.ecommerce.Core.config.modelMapper.IModelMapperService;
import dev.patika.ecommerce.Core.result.ResultData;
import dev.patika.ecommerce.Core.utilies.ResultHelper;
import dev.patika.ecommerce.Dto.request.product.ProductSaveRequest;
import dev.patika.ecommerce.Dto.request.supplier.SupplierSaveRequest;
import dev.patika.ecommerce.Dto.response.product.ProductResponse;
import dev.patika.ecommerce.Dto.response.supplier.SupplierResponse;
import dev.patika.ecommerce.entities.Category;
import dev.patika.ecommerce.entities.Product;
import dev.patika.ecommerce.entities.Supplier;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
    private final IProductService productService;
    private final IModelMapperService modelMapper;

    //Çoklu sınıf olduğu için değer servislerede ihtiyacımız var
    private final ICategoryService categoryService;
    private final ISupplierService supplierService;

    public ProductController(IProductService productService, IModelMapperService modelMapper, ICategoryService categoryService, ISupplierService supplierService) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.supplierService = supplierService;
    }

//////////////////////////////////////////////////////////////////////////////////////////
    //Burada SAVE işlemi farkılılık gösterecek çünkü Çoklu ilişki var (Many-To-One)
    //////////////////////////////////////////////////////////////////////////////////////////

    //SAVE (Yeni bir kullanıcı ekleme - CREATE)
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) //oluşturma (POST) statüsü
    public ResultData<ProductResponse> save(@Valid @RequestBody ProductSaveRequest productSaveRequest) {
        Product saveProduct = this.modelMapper.forRequest().map(productSaveRequest, Product.class);

        Category category = this.categoryService.get(productSaveRequest.getCategoryId());
        saveProduct.setCategory(category);

        Supplier supplier = this.supplierService.get(productSaveRequest.getSupplierId());
        saveProduct.setSupplier(supplier);

        this.productService.save(saveProduct);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveProduct, ProductResponse.class));
    }

    //GET (Kullanıcı listesini çekme - READ)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<ProductResponse> get(@PathVariable("id") int id) {
        Product product = this.productService.get(id);
        ProductResponse productResponse = this.modelMapper.forResponse().map(product, ProductResponse.class);
        return ResultHelper.succes(productResponse);
    }

    //GET (Sadece Supplier bilgisini çekmek istersek)
    @GetMapping("/{id}/supplier")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<SupplierResponse> getSupplier(@PathVariable("id") int id) {
        Product product = this.productService.get(id);
        SupplierResponse supplierResponse = this.modelMapper.forResponse().map(product.getSupplier(), SupplierResponse.class);
        return ResultHelper.succes(supplierResponse);
    }
}
