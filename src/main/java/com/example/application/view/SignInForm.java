package com.example.application.view;


import com.example.application.controller.MainPage;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

@Route("signIn")
@PageTitle("Game | Sign In")
public class SignInForm extends VerticalLayout {
    private TextField login = new TextField("Login");
    private TextField password = new TextField("Password");
    private Button signIn = new Button("Sign In");
    private RouterLink toHomePage = new RouterLink("To home page", MainPage.class);

    @Autowired
    public SignInForm(AuthenticationManager authenticationManager) {
        addClassName("signIn-form");
        setSizeFull();
        add(toHomePage, login, password, signIn);
        signIn.addClickListener((ComponentEventListener<ClickEvent<Button>>) event -> {
            try {
                final Authentication authentication = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(login.getValue(), password.getValue()));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                UI.getCurrent().navigate("/game");
            } catch (AuthenticationException e) {
                Notification.show("Login or/and password is/are invalid");
            }
        });
    }
}
