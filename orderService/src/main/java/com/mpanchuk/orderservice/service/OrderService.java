package com.mpanchuk.orderservice.service;

import com.mpanchuk.app.domain.CityDistancePair;
import com.mpanchuk.app.domain.StashPair;
import com.mpanchuk.app.domain.messaging.CreateOrderDto;
import com.mpanchuk.app.domain.response.OrderResponse;
import com.mpanchuk.app.exception.PriceException;
import com.mpanchuk.app.model.City;
import com.mpanchuk.app.model.Coupon;
import com.mpanchuk.app.model.Item;
import com.mpanchuk.app.repository.CityRepository;
import com.mpanchuk.app.repository.CouponRepository;
import com.mpanchuk.app.repository.ItemRepository;
import com.mpanchuk.app.repository.StashRepository;
import com.mpanchuk.app.service.JwtService;
import com.mpanchuk.app.util.graph.RouteFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
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

//    private final KafkaTemplate<String, Object> template;

    @KafkaListener(topics = "order-request", containerFactory = "singleFactory", groupId = "orders")
    public void makeOrder(CreateOrderDto createOrderDto) throws NoSuchElementException, PriceException {
        System.out.println(createOrderDto);
        String username = createOrderDto.getUsername();
        String destination = createOrderDto.getDestination();
        String coupon = createOrderDto.getCoupon();
        if (!checkOrderSum(username)) {
            throw new PriceException();
        }

        Optional<City> optionalCity = cityRepository.findByName(destination);
        if (optionalCity.isEmpty()) {
            throw new NoSuchElementException("city");
        }
        Optional<Coupon> cp = couponRepository.findByName(coupon);
        City destinationCity = cityRepository.findByName(destination).orElseThrow();
        List<StashPair<Item, Integer>> storage = stashRepository.getStorage(username);
        HashMap<String, CityDistancePair<String, Integer>> itemToCity = new HashMap<>();

        for (StashPair<Item, Integer> pair : storage) {
            Set<City> cities = itemRepository.findById(pair.getFirst().getId()).orElseThrow().getCities();
            int[] result = routeFinder.findRoute(destinationCity.getId(), cities);
            String cityNameFrom = cityRepository.findById((long) result[0]).orElseThrow().getName();
            if (result[1] > 1200) {
                itemToCity.put(pair.getFirst().getName(), new CityDistancePair<>("Невозможно доставить, дистанция больше 1200", result[1]));
            } else
                itemToCity.put(pair.getFirst().getName(), new CityDistancePair<>(cityNameFrom, result[1]));
        }


        int price = stashRepository.calcPrice(username);
        if (cp.isEmpty()) {
//            template.send("order-response", new OrderResponse(itemToCity, destination, price, 0, price, "Без купона"));
        }
        int discount = (int) (price * cp.get().getDiscount());
//        template.send("order-response", new OrderResponse(itemToCity, destination, price, discount, price - discount, "Купон применен"));
    }

    private boolean checkOrderSum(String username) {
        return stashRepository.calcPrice(username) > 1000;
    }
}
