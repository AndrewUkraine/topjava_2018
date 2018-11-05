package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> listDone = getFilteredWithExceededWithStream(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        listDone.forEach(System.out::println);

    }

    // done with cycles
    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> mapWithSummOfCalories = new HashMap<LocalDate, Integer>();

        for (UserMeal userMeal : mealList) {
            LocalDate localDateTime = userMeal.getDateTime().toLocalDate();

            mapWithSummOfCalories.put(localDateTime, mapWithSummOfCalories.getOrDefault(localDateTime, 0) + userMeal.getCalories());

        }

        List<UserMealWithExceed> userMealWith = new ArrayList<>();


        for (UserMeal userMeal : mealList) {

            LocalDateTime localDateTime = userMeal.getDateTime();

            if (TimeUtil.isBetween(localDateTime.toLocalTime(), startTime, endTime)) {
                userMealWith.add(new UserMealWithExceed(localDateTime, userMeal.getDescription(), userMeal.getCalories(),
                        mapWithSummOfCalories.get(localDateTime.toLocalDate()) > caloriesPerDay));
            }

        }

        return userMealWith;
    }


    // done with StremIP
    public static List<UserMealWithExceed> getFilteredWithExceededWithStream(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

Map <LocalDate, Integer> mapaWithSummByDay = mealList.stream().collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));


  return   mealList.stream().filter(um->TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
        .map(um -> new UserMealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(),
        mapaWithSummByDay.get(um.getDateTime().toLocalDate())> caloriesPerDay))
        .collect(Collectors.toList());
    }
}
