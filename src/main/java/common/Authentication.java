package common;

import common.exception.MustBeNotEmptyException;
import common.exception.RangeException;

import java.io.Serializable;

public class Authentication implements Serializable {
    private String userName;
    private String password;
    private Long id;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public void setUserName(String userName) throws RangeException, MustBeNotEmptyException {
        if (userName.length() > 127) throw new RangeException();
        if (userName.equals("")) throw new MustBeNotEmptyException();
        this.userName = userName;

    }

    public void setPassword(String userPassword) throws MustBeNotEmptyException, RangeException {
        this.password = userPassword;
        if(password.equals("")) throw new MustBeNotEmptyException();
        if (password.length() !=8) throw new RangeException();
    }

}
