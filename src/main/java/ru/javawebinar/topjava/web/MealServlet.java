package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


//@WebServlet("/products")
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    MealWithExceed mealWithExceed;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("you on my page / redirect to Meals");


       MealsUtil.getFilteredWithExceeded(MealsUtil.createWithExceed(List<Meal> meals, int caloriesPerDay))

        resp.sendRedirect("/meals.jsp");



        request.setAttribute("products", products); // Will be available as ${products} in JSP
        request.getRequestDispatcher("/WEB-INF/products.jsp").forward(request, response);

        List<Meal> meals = productService.list();
        request.setAttribute("products", products); // Will be available as ${products} in JSP


    }



}
