package com.example.fraizrtcp.net.service;

import com.example.fraizrtcp.data.Dao;
import com.example.fraizrtcp.model.User;

public class Controller implements ControllerInterface{
    private String email;
    private Dao dao;
    private User user;

    private static Controller controller;

    public static Controller getInstance() {
        if (controller == null) controller = new Controller();
        return controller;
    }
}
