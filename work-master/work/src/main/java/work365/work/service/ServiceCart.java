package work365.work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import work365.work.Repository.CheckoutRepo;
import work365.work.model.Category;
import work365.work.model.CheckoutCart;
import work365.work.model.Marque;
import work365.work.model.Recette;

import java.io.IOException;
import java.util.Base64;

@Service
public class ServiceCart {

    @Autowired
    CheckoutRepo checkoutRepo;


    public boolean  findById(Long id) {
        return checkoutRepo.findById(id).isPresent();
    }

    public void editStatut(long id, String statut )throws IOException {
        CheckoutCart checkoutCart = checkoutRepo.getById(id);
        checkoutCart.setStatut(statut);
        checkoutRepo.save(checkoutCart);
    }



}