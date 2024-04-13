package step.learning.dal.dao;

import com.google.inject.Inject;
import step.learning.dal.dto.CartItem;
import step.learning.dal.dto.Product;
import step.learning.dal.dto.User;
import step.learning.services.db.DbService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class CartDao {

    private final DbService dbService;

    @Inject
    public CartDao(DbService dbService) {
        this.dbService = dbService;
    }

    public List<CartItem> getCart() {
        return Arrays.asList( new CartItem[] {
                new CartItem(UUID.randomUUID(), UUID.randomUUID(), 1),
                new CartItem(UUID.randomUUID(), UUID.randomUUID(), 2),
                new CartItem(UUID.randomUUID(), UUID.randomUUID(), 3),
        });
    }
    public void add(String userdId, String productId, int cnt) {
        // Шукаємо чи є у користувача відкритий кошик
        String cartId = null ;
        String sql = String.format(
                "SELECT cart_id FROM carts WHERE cart_user='%s' AND cart_status=0 ",
                userdId);
        try(Statement statement = dbService.getConnection().createStatement()) {
            ResultSet res = statement.executeQuery(sql);
            if( res.next() ) {  // є відкритий кошик
                cartId = res.getString(1);
            }
            else {  // немає відкритого кошику
                cartId = UUID.randomUUID().toString();
                sql = String.format(
                        "INSERT INTO carts(cart_id,cart_user,cart_date,cart_status) " +
                                "VALUES('%s', '%s',CURRENT_TIMESTAMP,0)",
                        cartId, userdId );
                statement.executeUpdate(sql);
            }
            // cartId - id кошику чи старого, чи нового
            // Перевіряємо чи є у кошику такий товар
            sql = String.format(
                    "SELECT cart_dt_cnt FROM carts_details WHERE cart_id = '%s' AND product_id = '%s'",
                    cartId, productId);
            res = statement.executeQuery(sql);
            if( res.next() ) { // Якщо є, то збільшуємо кількість
                cnt += res.getInt(1);   // додаємо наявну та нову кількість
                sql = String.format(
                        "UPDATE carts_details SET cart_dt_cnt = %d WHERE cart_id = '%s' AND product_id = '%s'",
                        cnt, cartId, productId);
            }
            else {  // Якщо немає, то створюємо (додаємо)
                sql = String.format(
                        "INSERT INTO carts_details(cart_dt_id,cart_id,product_id,cart_dt_cnt ) " +
                                "VALUES( UUID(), '%s', '%s', %d )",
                        cartId, productId, cnt);
            }
            statement.executeUpdate(sql);
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println(sql);
        }
    }
}
