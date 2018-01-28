/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.mappers.cv;

import edu.marius.graph.domain.cv.Address;
import edu.marius.graph.entity.AddressType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MariusCraciunescu
 */
@Service
@Transactional
public class AddressMapper {

    public Address map(AddressType addressType) {
        Address address = new Address();
        address.setCity(addressType.getCity());
        address.setState(addressType.getState());
        address.setZip(addressType.getZip() + "");
        return address;
    }

    public AddressType map(Address address) {
        AddressType a = new AddressType();
        a.setCity(address.getCity());
        a.setState(address.getState());
        a.setZip(Integer.parseInt(address.getZip()));
        return a;
    }
}
