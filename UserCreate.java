package org.example;

import java.util.Random;

public class UserCreate {
    private String email = "myEmail" + new Random().nextInt(100000) + "@yandex.ru";
    private String password = "myPassword" + new Random().nextInt(100000);
    private String name = "myName" + new Random().nextInt(100000);

    public String getRandomName() {
        return this.name;
    }

    public String getRandomEmail() {
        return this.email;
    }

    public String getRandomPassword() {
        return this.password;
    }


}

