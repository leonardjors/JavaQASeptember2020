package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:config.properties")
public interface ServerConfig extends Config {

    @Key("otusURL")
    String otusURL();

    @Key("expectedTitle")
    String expectedTitile();

    @Key("yandexURL")
    String yandexURL();

    @Key("username")
    String username();

    @Key("password")
    String password();

    @Key("latinName")
    String latinName();

    @Key("latinLastName")
    String latinLastName();

    @Key("blogName")
    String blogName();

    @Key("birthdate")
    String birthdate ();

    @Key("viberNumber")
    String viberNumber();

    @Key("VKcontactInfo")
    String VKcontactInfo();






}
