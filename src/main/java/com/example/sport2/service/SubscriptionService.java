package com.example.sport2.service;

import com.example.sport2.dto.SubscriptionDTO;
import com.example.sport2.entity.Subscription;
import com.example.sport2.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<SubscriptionDTO> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream()
                .map(subscription -> new SubscriptionDTO(
                        subscription.getId(),
                        subscription.getType(),
                        subscription.getPrice(),
                        subscription.getSessionCount(),
                        subscription.getValidityInDays()))
                .collect(Collectors.toList());
    }

    public SubscriptionDTO createSubscription(SubscriptionDTO dto) {
        Subscription subscription = new Subscription();
        subscription.setType(dto.getType());
        subscription.setPrice(dto.getPrice());
        subscription.setSessionCount(dto.getSessionCount());
        subscription.setValidityInDays(dto.getValidityInDays());

        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return new SubscriptionDTO(
                savedSubscription.getId(),
                savedSubscription.getType(),
                savedSubscription.getPrice(),
                savedSubscription.getSessionCount(),
                savedSubscription.getValidityInDays()
        );
    }
}
