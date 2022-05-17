package com.FruitShopbackend.FruitShopapi.services.implementation;

import com.FruitShopbackend.FruitShopapi.exception_handling.exceptions.AlreadyExistsException;
import com.FruitShopbackend.FruitShopapi.exception_handling.exceptions.NotFoundException;
import com.FruitShopbackend.FruitShopapi.models.DTOS.AddressRequestDTO;
import com.FruitShopbackend.FruitShopapi.models.Entities.Address;
import com.FruitShopbackend.FruitShopapi.models.Entities.Cart;
import com.FruitShopbackend.FruitShopapi.models.Entities.UserEntity;
import com.FruitShopbackend.FruitShopapi.models.Entities.models.AddressType;
import com.FruitShopbackend.FruitShopapi.repo.AddressRepo;
import com.FruitShopbackend.FruitShopapi.repo.CartRepo;
import com.FruitShopbackend.FruitShopapi.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Override
    public Address post(UUID cartId, AddressType addressType, AddressRequestDTO addressFromRequest) {
        //The cart won't be empty cuz spring security should have thrown an exception already when trying to access it
        Optional<Cart> optionalCart = cartRepo.findById(cartId);
        Cart cart = optionalCart.get();

        List<Address> addressesFromCart = cart.getAddresses();
        addressesFromCart.forEach( address -> {
            if(address.getType().equals(addressType)) {
                throw new AlreadyExistsException("A "+ addressType.toString() + " address already exists");
            }
        });

        Address addressToBeSaved = new Address(null, addressType, addressFromRequest, cart, null);

        //The strategy is creating the address in user if user never added addresses for a previous delivery
        UserEntity userFromCart = cart.getUserEntityInCart();
        List<Address> addressesFromUser = userFromCart.getAddresses();
        Boolean addressWithGivenTypeExists = false;
        for(Address address : addressesFromUser) {
            if(address.getType().equals(addressType))
                addressWithGivenTypeExists = true;
        }

        if(!addressWithGivenTypeExists)
            addressToBeSaved.setUserEntityInAddress(userFromCart);

        return addressRepo.save(addressToBeSaved);

    }

    @Override
    public Address update(UUID cartId, AddressType addressType, AddressRequestDTO addressFromRequest) {
        Optional<Cart> optionalCart = cartRepo.findById(cartId);
        Cart cart = optionalCart.get();

        Boolean addressWithGivenTypeExists = false;
        UUID addressId = null;
        for(Address address : cart.getAddresses()) {
            if(address.getType().equals(addressType)){
                addressWithGivenTypeExists = true;
                addressId = address.getAddressId();
            }
        }
        if(!addressWithGivenTypeExists)
            throw new NotFoundException(addressType + " address doesn't exist in cart " + cartId);

        Address addressToBeModified = new Address(addressId, addressType, addressFromRequest, cart, null);

        return addressRepo.save(addressToBeModified);

    }

    //todo make it work
    @Override
    public Boolean delete(UUID cartId, AddressType addressType) {
        Optional<Cart> optionalCart = cartRepo.findById(cartId);

        Cart cart = optionalCart.get();
        Boolean addressWithGivenTypeExists = false;
        UUID addressId = null;
        for(Address address : cart.getAddresses()) {
            if(address.getType().equals(addressType)){
                addressWithGivenTypeExists = true;
                addressId = address.getAddressId();
            }
        }
        if(!addressWithGivenTypeExists)
            throw new NotFoundException(addressType + " address "+addressId+" doesn't exist");

        addressRepo.deleteById(addressId);
        return true;
    }
}
