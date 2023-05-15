package BusinessLogic;

import Model.Client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameClientValidator implements Validator<Client>{
    @Override
    public void validate(Client c) {
        Pattern ptr=Pattern.compile("^[a-zA-Z]+\\s+[a-zA-Z]+$");
        Matcher matc=ptr.matcher(c.getNume());
        if(!matc.matches())
            throw new IllegalArgumentException("Invalid client's name!\n");
    }
}
