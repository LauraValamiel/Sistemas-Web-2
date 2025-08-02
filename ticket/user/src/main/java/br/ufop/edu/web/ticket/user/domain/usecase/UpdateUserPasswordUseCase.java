package br.ufop.edu.web.ticket.user.domain.usecase;

//import br.ufop.edu.web.ticket.user.domain.UserDomain;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class UpdateUserPasswordUseCase {

    private String emailModel;
    private String emailPassed;
    private String old_passwordModel;
    private String old_passwordPassed;

    public void validate() {

        validateEmail();
        validateOldPassword();

    }

    private void validateEmail() {

        if(emailModel.equals(emailPassed)) {
            throw new RuntimeException("Invalid email");
        }
    }

    private void validateOldPassword() {

        if(!old_passwordModel.equals(old_passwordPassed)) {
            throw new RuntimeException("Invalid old password");
        }
    }

}