package com.westernhills.westernhills.controller.admincontroller;

import com.westernhills.westernhills.dto.ProductDto;
import com.westernhills.westernhills.entity.admin.Category;

import com.westernhills.westernhills.entity.admin.Image;
import com.westernhills.westernhills.entity.admin.Product;
import com.westernhills.westernhills.repo.CategoryRepository;
import com.westernhills.westernhills.repo.ProductRepository;
import com.westernhills.westernhills.service.interfaceService.CategoryService;
import com.westernhills.westernhills.service.interfaceService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Controller
public class ProductController{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;



    @Autowired
    private CategoryService categoryService;


    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping("/addProduct")
    public String selectProduct(Model model){
        List<Category> categories=categoryRepository.findAll()
                        .stream()
                                .filter(category ->!category.isDeleted())
                                     .collect(Collectors.toList());
        model.addAttribute("categories", categories);
        model.addAttribute("productDto",new ProductDto());
        return "admin/add-product";
    }




    @GetMapping("/showProduct")
    public String productShow(Model model){

        List<Product> products = productRepository.findAll()
                .stream()


                .filter(product -> {
                    Category category = product.getCategory();
                    return category != null && !category.isDeleted();
                })
                .collect(Collectors.toList());


        model.addAttribute("products", products);
        System.out.println(products);
        return "admin/product";
    }









    public static String uploadDir ="D:\\westernhills\\westernhills\\src\\main\\resources\\static\\all-Image";


    @PostMapping("/save-product")

    public String addProducts(@ModelAttribute("productDTO") ProductDto productDto,Model model,
                              @RequestParam("productImage") List<MultipartFile> files) throws IOException {
        Product product = new Product();
        product.setUuid(productDto.getId());
        product.setName(productDto.getName());
        product.setCategory(productDto.getCategory());
        product.setSelPrice(productDto.getSelPrice());
        product.setStock(productDto.getStock());
        product.setDescription(productDto.getDescription());



        List<Image> images = new ArrayList<>();


        for (MultipartFile file : files) {
            Image image = new Image();
            String imageUUID = file.getOriginalFilename();
            Path filenameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(filenameAndPath, file.getBytes());
            image.setFileName(imageUUID);
            image.setProduct_id(product);
            images.add(image);
        }
        product.setImages(images);
        productService.addProduct(product);
        model.addAttribute("products",productService.getAllProducts());

        return "redirect:/showProduct";


    }












    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable UUID id, RedirectAttributes attributes) {
        productService.deleteById(id);
        return "redirect:/showProduct";
    }



    @PostMapping("/update-product")
    public String updateProduct(@ModelAttribute("product") Product product,
                                @RequestParam("newProductImage") MultipartFile newFile,
                                Model model) throws IOException {

        Optional<Product> optionalExistingProduct = productRepository.findById(product.getUuid());

        if (optionalExistingProduct.isPresent()) {
            Product existingProduct = optionalExistingProduct.get();


            existingProduct.setName(product.getName());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setSelPrice(product.getSelPrice());
            existingProduct.setDescription(product.getDescription());


            if (!newFile.isEmpty()) {
                Image image = new Image();
                String imageUUID = newFile.getOriginalFilename();
                Path filenameAndPath = Paths.get(uploadDir, imageUUID);
                Files.write(filenameAndPath, newFile.getBytes());
                image.setFileName(imageUUID);
                image.setProduct_id(existingProduct);
                existingProduct.getImages().add(image);
            }


            productService.addProduct(existingProduct);
        }

        return "redirect:/showProduct";
    }






















}