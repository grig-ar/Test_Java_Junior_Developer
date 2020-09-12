package db.dao;

import db.entity.ShopCustomer;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class ShopCustomerDao {

    ShopCustomer getShopCustomerById(int id) {

    }

    List<ShopCustomer> getAllShopCustomers() {

    }

    List<ShopCustomer> getShopCustomersByLastName(String lastName) {

    }

    List<ShopCustomer> getShopCustomersByProductsAmount(String productName, int amount) {

    }

    List<ShopCustomer> getShopCustomersByPriceSum(BigDecimal lowerBound, BigDecimal upperBound) {

    }

    List<ShopCustomer> getShopCustomersByLeastProduct(int limit) {

    }

    String getAllInfo(Date lowerBound, Date upperBound) {

    }
}
