package com.westernhills.westernhills.service.interfaceService;

import com.westernhills.westernhills.entity.userEntity.User;
import com.westernhills.westernhills.entity.userEntity.UserAddress;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressService {


    List<UserAddress> findAll();

    void deleteUserAddress(UUID id);
    Optional<UserAddress> getUserAddressById(UUID  id);

    UserAddress save(UserAddress userAddress);

    void disableAddress(UUID uuid);

    List<UserAddress> findByUser(User user);

    void updateAddress(UUID id, UserAddress address);


}
