package com.example.application.view;


import com.example.application.controller.MainPage;
import com.example.application.entity.User;
import com.example.application.service.implementation.UserServiceImpl;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.RegexpValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;

@Route("registration")
@PageTitle("Game | Registration")
public class RegistrationForm extends VerticalLayout {
    @Autowired
    private UserServiceImpl userServiceImpl;

    private TextField email = new TextField("Email");
    private TextField login = new TextField("Login");
    private TextField password = new TextField("Password");
    private Button save = new Button("Save");
    private Binder<User> binder = new Binder<>(User.class);
    private RouterLink toHomePage = new RouterLink("To home page", MainPage.class);

    public RegistrationForm() {
        addClassName("registration-class");
        setSizeFull();

        binder.forField(email)
                .withValidator(new RegexpValidator("Invalid email", "^[^@\\s]+@[^@\\s\\.]+\\.[^@\\.\\s]+$"))
                .bind(User::getEmail, User::setEmail);


        add(toHomePage, email, login, password, save);
        save.addClickListener((ComponentEventListener<ClickEvent<Button>>) event -> saveEvent());
    }

    private void saveEvent() {
        String currentLogin = login.getValue();
        User userByCurrentLogin = userServiceImpl.findByLogin(currentLogin);
        if (userByCurrentLogin !=null) {
            Notification.show("User with this login already exists");
            return;
        }
        User user = new User();
        user.setEmail(email.getValue());
        user.setLogin(login.getValue());
        user.setPassword(password.getValue());
        userServiceImpl.save(user);
        UI.getCurrent().navigate("/signIn");
    }
}

