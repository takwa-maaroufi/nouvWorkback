package work365.work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import work365.work.Repository.MarqueRepository;
import work365.work.model.Marque;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class MarqueService {

    @Autowired
    MarqueRepository marqueRepository;


    public Marque store(MultipartFile file, MultipartFile file1, String nomMarque,
                          String teleMarque, String emailMarque) throws IOException {
        Marque m = new Marque();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName1 = StringUtils.cleanPath(file1.getOriginalFilename());
        if((fileName.contains("..")) && (fileName1.contains("..") ) )
        {
            System.out.println("not a a valid file");
        }
        try {
            m.setContrat(Base64.getEncoder().encodeToString(file.getBytes()));
            m.setLogo(Base64.getEncoder().encodeToString(file1.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        m.setNomMarque(nomMarque);
        m.setTeleMarque(teleMarque);
        m.setEmailMarque(emailMarque);

        return  marqueRepository.save(m);

    }


    public List<Marque> listMarque() {
        return marqueRepository.findAll();
    }


    public Optional<Marque> findBymrqId(int marqueId) {
        return marqueRepository.findById(marqueId);
    }

    public void editMarque( int marqueId, MultipartFile file, MultipartFile file1 , String nomMarque,
    String emailMarque , String teleMarque )throws IOException {
        Marque marque = marqueRepository.getById(marqueId);
        marque.setNomMarque(nomMarque);
        marque.setLogo(Base64.getEncoder().encodeToString(file1.getBytes()));
        marque.setEmailMarque(emailMarque);
        marque.setTeleMarque(teleMarque);
        marque.setContrat(Base64.getEncoder().encodeToString(file.getBytes()));
        marqueRepository.save(marque);
    }

    public boolean findById(int marqueId) {
        return marqueRepository.findById(marqueId).isPresent();
    }

    public void deleteMarque(Integer marqueId) {

        Optional<Marque> optionalMarque = marqueRepository.findById(marqueId);

        optionalMarque.ifPresent(marqueRepository::delete);

    }

     /*  public void createMarque(Marque marque) {
        marqueRepository.save(marque);
    }

       public void editMarque(int marqueId, Marque updateMarque ){
        Marque marque = marqueRepository.getById(marqueId);
        marque.setNomMarque(updateMarque.getNomMarque());
        marque.setLogo(updateMarque.getLogo());
        marque.setEmailMarque(updateMarque.getEmailMarque());
        marque.setTeleMarque(updateMarque.getTeleMarque());
        marque.setContrat(updateMarque.getContrat());
        marqueRepository.save(marque);
    }
*/

}
