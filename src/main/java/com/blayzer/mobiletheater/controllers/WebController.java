package com.blayzer.mobiletheater.controllers;

import com.blayzer.mobiletheater.model.entity.*;
import com.blayzer.mobiletheater.model.enums.EntityPurchaseType;
import com.blayzer.mobiletheater.model.payload.EntityAuth;
import com.blayzer.mobiletheater.model.payload.EntityPayment;
import com.blayzer.mobiletheater.model.enums.EntityPayloadStatus;
import com.blayzer.mobiletheater.model.payload.PayloadPromocode;
import com.blayzer.mobiletheater.model.payment.PaymentRequest;
import com.blayzer.mobiletheater.model.payment.PaymentResponse;
import com.blayzer.mobiletheater.model.repository.PromocodeRepository;
import com.blayzer.mobiletheater.model.repository.PurchaseRepository;
import com.blayzer.mobiletheater.model.repository.UserRepository;
import com.blayzer.mobiletheater.service.SpectacleService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping("/mobiletheater/api")
@Api(tags = "Mobile Theater API")
public class WebController {

    private final SpectacleService spectacleService;
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;
    private final PromocodeRepository promocodeRepository;
    private final RestTemplate restTemplate;

    @GetMapping("/v0")
    @ApiOperation("Get status API info")
    public String index(Model model) {
        String[] methods = new String[]{
            "/v0/spectacles/{uid}",
            "/v0/spectacle/{id}",
                "/v0/purshase",
                "/v0/promocode",
                "/v0/auth",
        };
        Gson gson = new GsonBuilder().create();
        return gson.toJson(methods);
    }

    @GetMapping("/v0/spectacles/{uid}")
    @ApiOperation("Get all spectacles list")
    public List<EntitySpectacle> spectacles(Model model, @PathVariable String uid) {
        List<EntitySpectacle> spectacles = spectacleService.getAll();
        List<EntityPurchase> purchases = purchaseRepository.getAllByUserUid(uid);
        if(purchases != null) {
            for(EntityPurchase purchase : purchases) {
                for(EntitySpectacle spectacle : spectacles) {
                    if(spectacle.price > 0 && purchase.spectacleId == spectacle.spectacleId) {
                        spectacle.isPurshased = true;
                    }
                }
            }
        }

        //Sort spectacles (delete this, if do sorting into entity)
        Collections.sort(spectacles, Comparator.comparingInt(EntitySpectacle::getSpectacleId).reversed());
        for(EntitySpectacle spectacle : spectacles) {
            Collections.sort(spectacle.chapters, Comparator.comparingInt(EntityChapter::getChapterId));
        }

        log.info("User send spectacles request: " + uid);
        return spectacles;
    }

//    @GetMapping("/v0/spectacle/{id}")
//    @ApiOperation("Get spectacle by spectacleId")
//    public EntitySpectacle news(Model model, @PathVariable Integer id) {
//        Optional<EntitySpectacle> spectacleEntity = spectacleService.getByID(id);
//        if (!spectacleEntity.isPresent()) {
//            //Метод не используется на данный момент
//            //Тем не менее, сделать как в первом методе
//            return new EntitySpectacle();
//        }
//
//        return spectacleEntity.get();
//    }

    @PostMapping("/v0/purshase")
    @ApiOperation("Do payment")
    public EntityPayment purshase(@RequestBody EntityPayment payment) {
        EntityPurchase entityPurchase = new EntityPurchase(payment.spectacleId, payment.user_uuid, EntityPurchaseType.CARD.toString());
        Optional<EntitySpectacle> spectacle = spectacleService.getByID(payment.spectacleId);
        if(entityPurchase != null && spectacle.isPresent()) {

            PaymentRequest request = new PaymentRequest();
            PaymentRequest.Amount amount = new PaymentRequest.Amount();
            request.payment_token = payment.paymentToken;
            amount.setCurrency("RUB");
            amount.setValue(spectacle.get().getPrice().toString());
            request.setAmount(amount);
            request.capture = false;
            request.description = "Оплата доступа к спектаклю " + spectacle.get().name;

            PaymentResponse response = createPayment(request);

            //Подтверждение платежа
            if(response.getStatus().equals("waiting_for_capture")) {

                PaymentResponse agreePayment = agreePayment(request.getAmount(), response.getId());
                if(agreePayment.getStatus().equals("succeeded")) {
                    purchaseRepository.save(entityPurchase);
                    payment.status = EntityPayloadStatus.SUCCESS;
                } else {
                    payment.status = EntityPayloadStatus.FAIL;
                }

            } else if(response.getStatus().equals("pending")) {
                //Возврат ответа с требованием подтвердить по 3DSecure
                payment.confirmationUrl = response.getConfirmation().getConfirmation_url();
                payment.paymentId = response.getId();
            } else if(response.getStatus().equals("succeeded")) {
                purchaseRepository.save(entityPurchase);
                payment.status = EntityPayloadStatus.SUCCESS;
            }
        } else {
            payment.status = EntityPayloadStatus.FAIL;
        }
        log.info("User send purshase request: " + payment + " | Status: " + payment.status);
        return payment;
    }

    @PostMapping("/v0/checkPayment/{uid}")
    @ApiOperation("Do payment check")
    public EntityPayment checkPayment(@RequestBody EntityPayment payment, @PathVariable String uid) {
        EntityPurchase entityPurchase = new EntityPurchase(payment.spectacleId, payment.user_uuid, EntityPurchaseType.CARD.toString());
        Optional<EntitySpectacle> spectacle = spectacleService.getByID(payment.spectacleId);
        if(entityPurchase != null && spectacle.isPresent()) {

            PaymentRequest request = new PaymentRequest();
            PaymentRequest.Amount amount = new PaymentRequest.Amount();
            request.payment_token = payment.paymentToken;
            amount.setCurrency("RUB");
            amount.setValue(spectacle.get().getPrice().toString());
            request.setAmount(amount);
            request.capture = false;
            request.description = "Оплата доступа к спектаклю " + spectacle.get().name;

            PaymentResponse response = getPayment(uid);

            if(response.getStatus().equals("waiting_for_capture")) {

                PaymentResponse agreePayment = agreePayment(request.getAmount(), response.getId());
                if(agreePayment.getStatus().equals("succeeded")) {
                    purchaseRepository.save(entityPurchase);
                    payment.status = EntityPayloadStatus.SUCCESS;
                } else {
                    payment.status = EntityPayloadStatus.FAIL;
                }
            } else if(response.getStatus().equals("succeeded")) {
                purchaseRepository.save(entityPurchase);
                payment.status = EntityPayloadStatus.SUCCESS;
            }
        } else {
            payment.status = EntityPayloadStatus.FAIL;
        }
        log.info("User send check purshase request: " + payment + " | Status: " + payment.status);
        return payment;
    }

    @PostMapping("/v0/promocode")
    @ApiOperation("Do promocode")
    public PayloadPromocode promocode(@RequestBody PayloadPromocode promocode) {
        EntityPromocode entityPromocode = promocodeRepository.getEntityPromocodeByPromocode(promocode.promocode);
        if(entityPromocode != null && promocode.user_uuid != null) {
            EntityPurchase entityPurchase = new EntityPurchase(entityPromocode.spectacleId, promocode.user_uuid, EntityPurchaseType.PROMOCODE.toString());
            purchaseRepository.save(entityPurchase);
            promocode.spectacleId = entityPromocode.spectacleId;
            promocode.status = EntityPayloadStatus.SUCCESS;
        } else {
            promocode.status = EntityPayloadStatus.FAIL;
        }
        log.info("User send promocode request: " + promocode + " | Status: " + promocode.status);
        return promocode;
    }

    @PostMapping("/v0/auth")
    @ApiOperation("Do auth")
    public EntityAuth auth(@RequestBody EntityAuth auth) {
        //В будущем реализовать проверку токена авторизации в firebase
        EntityUser entityUser = new EntityUser(auth.phoneNumber, auth.uuid);
        if(entityUser != null && entityUser.uuid.length() == 28) {
            userRepository.save(entityUser);
            auth.status = EntityPayloadStatus.SUCCESS;
        } else {
            auth.status = EntityPayloadStatus.FAIL;
        }
        log.info("User send auth request: " + auth + " | Status: " + auth.status);
        return auth;
    }

    @PostMapping("/v0/createSpectacle")
    @ApiOperation("Do Create Spectacle")
    public EntitySpectacle createSpectacle(@RequestHeader Map<String, String> headers, @RequestBody String spectacle) {
        if(headers.containsKey("authtoken") && headers.get("authtoken").equals("oXekBeY8HDshxXtF=YwHfFKpzpH6fOhY2FzhQA0LyYOsZ87!4z9GQkaQF8i0pkAK")) {
            EntitySpectacle entitySpectacle = new Gson().fromJson(spectacle, EntitySpectacle.class);
            spectacleService.saveSpectacle(entitySpectacle);
            return entitySpectacle;
        } else {
            return null;
        }
    }

    private PaymentResponse createPayment(PaymentRequest mrRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("927404", "test_tLUY80qa8dW_Qb3dYLN7Yb9jXgyB8pV3L2j26KZaw-s");
        headers.set("Idempotence-Key", UUID.randomUUID().toString());

        RequestEntity<PaymentRequest> requestEntity = new RequestEntity<>(
                mrRequest, headers, HttpMethod.POST, URI.create("https://api.yookassa.ru/v3/payments"));
        ResponseEntity<PaymentResponse> responseEntity = restTemplate.
                exchange(requestEntity, PaymentResponse.class);

        PaymentResponse response = responseEntity.getBody();
        return response;
    }

    private PaymentResponse getPayment(String paymentId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("927404", "test_tLUY80qa8dW_Qb3dYLN7Yb9jXgyB8pV3L2j26KZaw-s");

        RequestEntity<String> requestEntity = new RequestEntity<>(
                null, headers, HttpMethod.GET, URI.create("https://api.yookassa.ru/v3/payments/"+paymentId));
        ResponseEntity<PaymentResponse> responseEntity = restTemplate.
                exchange(requestEntity, PaymentResponse.class);

        PaymentResponse response = responseEntity.getBody();
        return response;
    }

    private PaymentResponse agreePayment(PaymentRequest.Amount mrRequest, String paymentId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("927404", "test_tLUY80qa8dW_Qb3dYLN7Yb9jXgyB8pV3L2j26KZaw-s");
        headers.set("Idempotence-Key", UUID.randomUUID().toString());

        RequestEntity<PaymentRequest.Amount> requestEntity = new RequestEntity<>(
                mrRequest, headers, HttpMethod.POST, URI.create("https://api.yookassa.ru/v3/payments/"+paymentId+"/capture"));
        ResponseEntity<PaymentResponse> responseEntity = restTemplate.
                exchange(requestEntity, PaymentResponse.class);

        PaymentResponse response = responseEntity.getBody();
        return response;
    }
}
