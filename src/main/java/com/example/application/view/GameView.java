package com.example.application.view;


import com.example.application.controller.MainPage;
import com.example.application.entity.Attempt;
import com.example.application.entity.User;
import com.example.application.repository.AttemptRepository;
import com.example.application.service.Game;
import com.example.application.service.implementation.NumberService;
import com.example.application.service.implementation.UserServiceImpl;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

@Route("game")
@PageTitle("Game")
public class GameView extends VerticalLayout {
    private static final int SIZE = 4;

    private TextField yourNumber = new TextField("Your number");
    private Button play = new Button("Play");
    private Button newGame = new Button("Start new game");
    private Button logout = new Button("Logout");
    private RouterLink toHomePage = new RouterLink("To home page", MainPage.class);
    private Game game;


    private VerticalLayout resultsLayout = new VerticalLayout();
    private HorizontalLayout buttonsLayout = new HorizontalLayout();
    Scroller scroller = new Scroller(new Div(resultsLayout));

    @Autowired
    private NumberService numberService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private AttemptRepository attemptRepository;

    public GameView() {
        setSizeFull();
        play.setEnabled(false);
        buttonsLayout.add(newGame, play, logout);
        add(toHomePage, buttonsLayout, yourNumber, scroller);
        yourNumber.setMaxLength(SIZE);
        yourNumber.setPattern("[0-9]+");
        yourNumber.setPreventInvalidInput(true);


        newGame.addClickListener(event -> {
            game = new Game(numberService.generateNumber(SIZE));
            play.setEnabled(true);
            resultsLayout.removeAll();
        });

        play.addClickListener(event -> {
            String yourNumber = this.yourNumber.getValue();
            Game.AttemptResult attempt = game.attempt(yourNumber);
            Label label = new Label(game.getAttempts() + ". " + yourNumber + " -- " + attempt.getAttemptResult());
            resultsLayout.add(label);

            scroller.setScrollDirection(Scroller.ScrollDirection.VERTICAL);
            if (attempt.getWinOrNot()) {
                saveCurrentUserResult(game.getAttempts());
            }
        });

        logout.addClickListener(event -> {
            SecurityContextHolder.clearContext();
            UI.getCurrent().navigate("/");
        });
    }

    public void saveCurrentUserResult(int attempts) {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findByLogin(currentUserName);

        Attempt attempt = new Attempt();

        attempt.setUser(currentUser);
        attempt.setDateOfAttempt(LocalDateTime.now());
        attempt.setNumberOfAttempts(attempts);
        attemptRepository.save(attempt);
    }


}
