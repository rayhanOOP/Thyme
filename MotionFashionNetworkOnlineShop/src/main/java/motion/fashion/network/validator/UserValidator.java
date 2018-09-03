package motion.fashion.network.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import motion.fashion.network.dao.UserDAO;
import motion.fashion.network.formbean.UserForm;
import motion.fashion.network.model.User;
 
@Component
public class UserValidator implements Validator {
 
    // common-validator library.
    private EmailValidator emailValidator = EmailValidator.getInstance();
 
    @Autowired
    private UserDAO UserDAO;
 
    // The classes are supported by this validator.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == UserForm.class;
    }
 
    @Override
    public void validate(Object target, Errors errors) {
        UserForm UserForm = (UserForm) target;
 
        // Check the fields of UserForm.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.UserForm.userName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.UserForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.UserForm.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.UserForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.UserForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.UserForm.confirmPassword");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.UserForm.gender");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryCode", "NotEmpty.UserForm.countryCode");
 
        if (!this.emailValidator.isValid(UserForm.getEmail())) {
            // Invalid email.
            errors.rejectValue("email", "Pattern.UserForm.email");
        } else if (UserForm.getUserId() == null) {
            User dbUser = UserDAO.findUserByEmail(UserForm.getEmail());
            if (dbUser != null) {
                // Email has been used by another account.
                errors.rejectValue("email", "Duplicate.UserForm.email");
            }
        }
 
        if (!errors.hasFieldErrors("userName")) {
            User dbUser = UserDAO.findUserByUserName(UserForm.getUserName());
            if (dbUser != null) {
                // Username is not available.
                errors.rejectValue("userName", "Duplicate.UserForm.userName");
            }
        }
 
        if (!errors.hasErrors()) {
            if (!UserForm.getConfirmPassword().equals(UserForm.getPassword())) {
                errors.rejectValue("confirmPassword", "Match.UserForm.confirmPassword");
            }
        }
    }
 
}
