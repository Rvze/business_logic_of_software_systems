package com.mpanchuk.app.service;

import com.mpanchuk.app.domain.CityDistancePair;
import com.mpanchuk.app.domain.messaging.CreateOrderDto;
import com.mpanchuk.app.domain.response.OrderResponse;
import com.mpanchuk.app.exception.PriceException;
import com.mpanchuk.app.model.*;
import com.mpanchuk.app.repository.*;
import com.mpanchuk.app.util.graph.RouteFinder;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final StashRepository stashRepository;
    private final CityRepository cityRepository;
    private final ItemRepository itemRepository;
    private final CouponRepository couponRepository;

    private final RouteFinder routeFinder;

    private final OrderRepository orderRepository;


    public OrderResponse makeOrder(CreateOrderDto createOrderDto) throws NoSuchElementException, PriceException {
        System.out.println("MEssage :  " + createOrderDto);
        String username = createOrderDto.getUsername();
        String destination = createOrderDto.getDestination();
        String coupon = createOrderDto.getCoupon();
        if (!checkOrderSum(username)) {
            throw new PriceException();
        }

        Optional<City> optionalCity = cityRepository.findByName(destination);
        if (optionalCity.isEmpty()) {
            throw new NoSuchElementException();
        }
        Optional<Coupon> cp = couponRepository.findByName(coupon);
        City destinationCity = cityRepository.findByName(destination).orElseThrow();
        Stash storage = stashRepository.getStorage(username);
        HashMap<String, CityDistancePair<String, Integer>> itemToCity = new HashMap<>();

        for (Item pair : storage.getItems()) {
            Set<City> cities = itemRepository.findById(pair.getId()).orElseThrow().getCities();
            int[] result = routeFinder.findRoute(destinationCity.getId(), cities);
            String cityNameFrom = cityRepository.findById((long) result[0]).orElseThrow().getName();
            if (result[1] > 1200) {
                itemToCity.put(pair.getName(), new CityDistancePair<>("Невозможно доставить, дистанция больше 1200", result[1]));
            } else
                itemToCity.put(pair.getName(), new CityDistancePair<>(cityNameFrom, result[1]));
        }


        int price = stashRepository.calcPrice(username);
        if (cp.isEmpty()) {
            orderRepository.save(Order.builder().destination(destination).price(price).totalPrice(price).discount(0).message("Без купона").build());
            return new OrderResponse(itemToCity, destination, price, 0, price, "Без купона");
        }
        int discount = (int) (price * cp.get().getDiscount());
        orderRepository.save(Order.builder().destination(destination).price(price).totalPrice(price - discount).discount(discount).message("Без купона").build());
        return new OrderResponse(itemToCity, destination, price, discount, price - discount, "Купон применен");
    }

    private boolean checkOrderSum(String username) {
        System.out.println(stashRepository.getStorage(username));
        return stashRepository.calcPrice(username) > 1000;
    }
}
