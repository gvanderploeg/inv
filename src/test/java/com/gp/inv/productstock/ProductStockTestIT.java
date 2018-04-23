package com.gp.inv.productstock;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This inflates the Entity Manager, runs the schema definition, thereby testing the basic entity setup.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductStockTestIT {

    @Autowired
    private ProductStockRepository productStockRepository;

    @Test
    public void testSchema() {
        MatcherAssert.assertThat(productStockRepository.count(), IsEqual.equalTo(1L));
    }

}
