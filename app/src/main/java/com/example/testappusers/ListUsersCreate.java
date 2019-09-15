package com.example.testappusers;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class ListUsersCreate {

    public static Gson gson = new Gson();
    static List<User> userList;


    public static List<User> getUserList(InputStream inputStream) {
        String jsonStr = "";
        try {
            int sym;
            while ((sym = inputStream.read()) != -1) {
                jsonStr += (char) sym;
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(jsonStr);


        Type listType = new TypeToken<List<User>>() {
        }.getType();


        userList = gson.fromJson(jsonStr, listType);

        return userList;
    }
}