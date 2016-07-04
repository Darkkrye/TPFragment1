package fr.esgi.retrofit;

/**
 * Created by Pierre on 14/06/2016.
 */
public class MyVariables { //à supprimer ! :P
    public static User user; //Correction très mauvaise idée de stocker en static sous android, la JVM peux la vider si l'application est réouverte, il relancera l'activité avec son intent
}
