package com.gp.inv.productstock.scheduler;

import com.gp.inv.productstock.ProductStock;
import com.gp.inv.productstock.ProductStockRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SelectPromotionsTest {

    @Mock
    private ProductStockRepository productStockRepository;

    @InjectMocks
    private SelectPromotions selectPromotions;

    @Before
    public void setUp() {
        selectPromotions = new SelectPromotions(productStockRepository);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void selectPromotions() {
        List<ProductStock> productsInStock = new ArrayList<>();

        ProductStock eligable = new ProductStock();
        eligable.setName("blauwe sokken");
        eligable.setProjectedOutOfStockDate(Date.from(Instant.now().plus(250, ChronoUnit.DAYS)));
        eligable.setNrInStock(150);
        productsInStock.add(eligable);

        ProductStock notEligable = new ProductStock();
        notEligable.setName("gele sokken");
        notEligable.setProjectedOutOfStockDate(Date.from(Instant.now().plus(50, ChronoUnit.DAYS)));
        notEligable.setNrInStock(150);
        productsInStock.add(notEligable);

        when(productStockRepository.findByNrInStockGreaterThan(100)).thenReturn(productsInStock.stream());

        selectPromotions.run();

        assertThat(eligable.isEligableForPromotion(), is(true));
        assertThat(notEligable.isEligableForPromotion(), is(false));


        verify(productStockRepository).save(eligable);
        verify(productStockRepository, never()).save(notEligable);
    }
}
