package com.example.application.controller;


import com.example.application.view.GameView;
import com.example.application.view.RegistrationForm;
import com.example.application.view.SignInForm;
import com.example.application.view.UserRatingView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Route("")
public class MainPage extends VerticalLayout {

    public MainPage() {
        RouterLink registrationLink = new RouterLink("Registration", RegistrationForm.class);
        RouterLink signInLink = new RouterLink("Sign In", SignInForm.class);
        RouterLink userRating = new RouterLink("Rating", UserRatingView.class);
        Button logout = new Button("Logout");

        Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
        if (authenticated == null) {
            add(registrationLink, signInLink, userRating);
        } else {
            RouterLink gameLink = new RouterLink("Game", GameView.class);
            add(gameLink, userRating, logout);
        }
        logout.addClickListener(event -> {
            SecurityContextHolder.clearContext();
            UI.getCurrent().getPage().reload();
        });
    }
}

