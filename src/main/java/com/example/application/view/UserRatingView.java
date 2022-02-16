package com.example.application.view;


import com.example.application.controller.MainPage;
import com.example.application.entity.Attempt;
import com.example.application.repository.AttemptRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.util.List;
import java.util.stream.Collectors;

@Route("rating")
@PageTitle("Rating")
public class UserRatingView extends VerticalLayout {
    private RouterLink toHomePage = new RouterLink("To home page", MainPage.class);
    private Grid<Attempt> attemptGrid;


    public UserRatingView(AttemptRepository attemptRepository) {
        Grid<RatingItem> grid = new Grid<>();
        grid.addColumn(it -> it.getLogin()).setHeader("Login");
        grid.addColumn(it -> it.getAverage()).setHeader("Average");

        List<RatingItem> collect1 = attemptRepository.findAll().stream()
                .collect(Collectors.groupingBy(it -> it.getUser().getLogin(),
                        Collectors.averagingDouble(Attempt::getNumberOfAttempts)))
                .entrySet().stream()
                .map(it -> new RatingItem(it.getKey(), it.getValue()))
                .collect(Collectors.toList());

        grid.setItems(collect1);

        VerticalLayout content = new VerticalLayout();
        content.add(grid);
        content.setSizeFull();
        add(toHomePage, content);
    }

    private static class RatingItem {
        private String login;
        private Double average;

        public RatingItem(String login, Double average) {
            this.login = login;
            this.average = average;
        }

        public String getLogin() {
            return login;
        }

        public Double getAverage() {
            return average;
        }
    }

}
