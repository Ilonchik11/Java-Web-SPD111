package step.learning.dal.dao;

import com.google.inject.Inject;
import step.learning.dal.dto.Product;
import step.learning.services.db.DbService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class ProductDao {
    private final DbService dbService;
    @Inject
    public ProductDao(DbService dbService) {
        this.dbService = dbService;
    }
    public boolean add( Product product ) {
        if( product == null ) return false ;
        if( product.getId() == null ) product.setId( UUID.randomUUID() );

        String sql = "INSERT INTO Products" +
                "(product_id,product_name,product_price,product_description,product_image ) " +
                "VALUES(?,?,?,?,?)";
        try( PreparedStatement prep = dbService.getConnection().prepareStatement(sql) ) {
            prep.setString( 1, product.getId().toString() );   // у JDBC відлік від 1
            prep.setString( 2, product.getName() );
            prep.setDouble( 3, product.getPrice() );
            prep.setString( 4, product.getDescription() );
            prep.setString( 5, product.getImage() );
            prep.executeUpdate();
            return true;
        }
        catch (SQLException ex) {
            System.err.println( ex.getMessage() );
            System.out.println( sql );
            return false ;
        }
    }
}
