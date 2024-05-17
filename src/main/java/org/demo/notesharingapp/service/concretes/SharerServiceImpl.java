package org.demo.notesharingapp.service.concretes;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.demo.notesharingapp.dao.SharerRepository;
import org.demo.notesharingapp.entity.Sharer;
import org.demo.notesharingapp.service.abstracts.SharerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SharerServiceImpl implements SharerService {

    private final SharerRepository sharerRepository; // Repository for interacting with Sharer data in the database
    private final Validator validator; // Validator used for performing validation operations on Sharer objects

    /**
     * Constructor for SharerServiceImpl class.
     *
     * @param sharerRepository Repository for interacting with Sharer data in the database.
     *                         This is injected by Spring framework through constructor injection.
     * @param validator        Validator used for performing validation operations on Sharer objects.
     *                         This is injected by Spring framework through constructor injection.
     */
    @Autowired // This annotation indicates that the constructor will be automatically wired by Spring framework
    public SharerServiceImpl(SharerRepository sharerRepository, Validator validator) {
        this.sharerRepository = sharerRepository; // Assigning the injected SharerRepository instance to the local variable
        this.validator = validator; // Assigning the injected Validator instance to the local variable
    }

    @Override
    public Sharer saveSharer(Sharer sharer) {

        // Username must be unique, check for any existing user with the same username
        if (sharerRepository.findSharerByUserName(sharer.getUserName()) != null) {
            throw new RuntimeException("Bu kullanıcı adı zaten kullanımda.");
        }

        // Email address must be unique, check for any existing user with the same email address
        if (sharerRepository.findByEmail(sharer.getEmail()) != null) {
            throw new RuntimeException("Bu e-posta adresi zaten kullanımda.");
        }

        // Validate the Sharer object using the Validator
        Set<ConstraintViolation<Sharer>> violations = validator.validate(sharer);

        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<Sharer> violation : violations) {
                errorMessage.append(violation.getMessage()).append("\n");
            }
            throw new RuntimeException(errorMessage.toString());
        }
        /*Here, we're checking if the violations set is not empty,
        indicating that there are validation violations.
        If violations are found,
        we construct an error message by iterating over each
        ConstraintViolation object in the set and appending its error message
        to a StringBuilder. Each error message is separated by a newline character (\n).
        Finally, we throw a RuntimeException containing the concatenated error messages.
        This mechanism ensures that if there are any validation errors detected during the
        validation process, the method will immediately terminate and throw a RuntimeException
        with detailed error messages, providing feedback to the caller about what went wrong. */


        // If the Sharer object passes validation, save it using the repository and return the saved object
        return sharerRepository.save(sharer);
    }



    @Override
    public Sharer updateSharer(int sharerId, Sharer updatedSharer) {
        // Find the Sharer object to be updated in the database
        Sharer existingSharer = sharerRepository.findById(sharerId)
                .orElseThrow(() -> new EntityNotFoundException("Şu kimliğe sahip paylaşan bulunamadı: " + sharerId));

        BeanUtils.copyProperties(updatedSharer, existingSharer, "id");

       /*   It updates only the requested fields using the copyProperties method of the BeanUtils class.
            This method is used to copy the properties of one object to another object.
            It takes two objects: source object and target object. Properties in the source object are copied to properties of the same name in the target object.
                The "id" parameter is a list of properties that should not be copied. That is, the properties in this list are not copied.
                In this case, the "id" parameter ensures that the id property of the existingSharer object is not copied.
                Copies non-null properties from the updatedSharer object to the existingSharer object.


        */// Save the updated Sharer object
        return sharerRepository.save(existingSharer);
    }


    @Override
    public void deleteSharer(int sharerId) {
        Sharer existingSharer = sharerRepository.findById(sharerId)
                .orElseThrow(() -> new EntityNotFoundException("Şu kimliğe sahip paylaşan bulunamadı: " + sharerId));

        // Delete the Sharer object from the database
        sharerRepository.delete(existingSharer);
    }

    @Override
    public List<Sharer> getSharers() {
        return sharerRepository.findAll();
    }

    @Override
    public Sharer getSharerById(int sharerId) {
        return sharerRepository.findById(sharerId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Şu kimliğe sahip paylaşan bulunamadı: " + sharerId));
    }

    @Override
    public Sharer getSharerByEmail(String email) {
        Sharer sharer = sharerRepository.findByEmail(email);
        if (sharer != null) {
            return sharer;
        } else {
            throw new EntityNotFoundException("Şu emaile sahip paylaşan bulunamadı: " + email);
        }
    }

    @Override
    public Sharer getSharerByUsername(String username) {
        Sharer sharer = sharerRepository.findSharerByUserName(username);
        if (sharer != null) {
            return sharer;
        } else {
            throw new EntityNotFoundException("Şu kullanıcı adına sahip paylaşan bulunamadı: " + username);
        }
    }


}
