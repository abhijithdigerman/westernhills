package com.westernhills.westernhills.service.interfaceService;

import com.westernhills.westernhills.entity.admin.Product;
import com.westernhills.westernhills.entity.userEntity.Cart;
import com.westernhills.westernhills.entity.userEntity.CheckOut;
import com.westernhills.westernhills.entity.userEntity.OrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CheckOutService {

    double getTotalPrice(String username);

    List<CheckOut> getCartItems(String username,UUID id);

    List<CheckOut> getCartItemsAll(String username, UUID id);

    List<CheckOut> getOnlineCheckout(String username, UUID id);

    List<CheckOut> getAllOrder(String username);

    void addToCartItem(String userName, UUID productId);


    List<CheckOut> findAll();

    Optional<CheckOut> canselProduct(UUID id);

    Optional<CheckOut> orderReturn(UUID id);


    Optional<CheckOut> getProductId(UUID id);


    void  orderStatusSetting(OrderStatus orderStatus,UUID id);


    void  orderCancellationAndMoneyTransferringInWallet(OrderStatus orderStatus,UUID id,String userName);










}
