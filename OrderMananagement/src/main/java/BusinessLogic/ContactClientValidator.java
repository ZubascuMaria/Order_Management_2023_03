package BusinessLogic;

import Model.Client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactClientValidator implements Validator<Client>{
    @Override
    public void validate(Client client) {
        Pattern ptr=Pattern.compile("^0[0-9]{9}$");
        Matcher matc=ptr.matcher(client.getContact());
        if(!matc.matches())
            throw new IllegalArgumentException("Invalid client's contact!\n");
    }
}
