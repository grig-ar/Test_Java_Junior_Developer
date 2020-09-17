package controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import model.criteria.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.*;

public class CriteriaBuilder {

    private CriteriaBuilder() {

    }

    public static CriteriasCollection buildCriterias(@NotNull JsonObject object) {
        Objects.requireNonNull(object);

        List<Criteria> criteriaList = new ArrayList<>();
        JsonArray jsonArray = object.get("criterias").getAsJsonArray();

        for (JsonElement element : jsonArray) {
            evaluateCriteria(criteriaList, element);
        }

        return new CriteriasCollection(criteriaList);
    }

    private static void evaluateCriteria(List<Criteria> criteriaList, JsonElement element) {
        String criteriaName = element.getAsJsonObject().keySet().iterator().next().toLowerCase();

        switch (criteriaName) {
            case "lastname": {
                evaluateLastName(criteriaList, element);
                break;
            }

            case "badcustomers": {
                evaluateBadCustomers(criteriaList, element);
                break;
            }

            case "productname": {
                evaluateProductName(criteriaList, element);
                break;
            }

            case "minexpenses": {
                evaluateExpenses(criteriaList, element);
                break;
            }

            default: {
                throw new IllegalArgumentException("Incorrect criteria");
            }
        }
    }

    private static void evaluateLastName(List<Criteria> criteriaList, JsonElement criteria) {
        String lastName = criteria.getAsJsonObject().entrySet().iterator().next().getValue().toString();
        lastName = lastName.replaceAll("^\"|\"$", "");

        criteriaList.add(new LastNameCriteria(lastName));
    }

    private static void evaluateBadCustomers(List<Criteria> criteriaList, JsonElement criteria) {
        int badCustomerAmount = criteria.getAsJsonObject().entrySet().iterator().next().getValue().getAsInt();

        criteriaList.add(new AmountCriteria(badCustomerAmount));
    }

    private static void evaluateProductName(List<Criteria> criteriaList, JsonElement criteria) {
        Iterator<Map.Entry<String, JsonElement>> iterator = criteria.getAsJsonObject().entrySet().iterator();
        String productName = iterator.next().getValue().toString();
        productName = productName.replaceAll("^\"|\"$", "");
        int productAmount = iterator.next().getValue().getAsInt();

        criteriaList.add(new ProductAmountCriteria(productName, productAmount));
    }

    private static void evaluateExpenses(List<Criteria> criteriaList, JsonElement criteria) {
        Iterator<Map.Entry<String, JsonElement>> iterator = criteria.getAsJsonObject().entrySet().iterator();
        BigDecimal min = iterator.next().getValue().getAsBigDecimal();
        BigDecimal max = iterator.next().getValue().getAsBigDecimal();

        criteriaList.add(new ExpensesCriteria(min, max));
    }

}
