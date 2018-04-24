package com.gp.inv.productstock.scheduler;

import com.gp.inv.productstock.ProductStockRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Job that marks stock as promoted, based on its projected out-of-stock-date and its nr-in-stock.
 */
@Component
public class SelectPromotions {

    private static final Logger LOG = LoggerFactory.getLogger(SelectPromotions.class);

    /**
     * Minimum number to have in stock before marking for promotion
     */
    private static final int NR_IN_STOCK_THRESHOLD = 100;

    /**
     * Minimum number of days to have stock (projected) before marking for promotion
     */
    private static final int OUT_OF_STOCK_DATE_WINDOW = 100;

    private ProductStockRepository productStockRepository;

    public SelectPromotions(ProductStockRepository productStockRepository) {
        this.productStockRepository = productStockRepository;
    }

    @Scheduled(fixedRate = 10000)
    @Transactional
    public void run() {
        LOG.trace("Running selection process for promotion.");

        productStockRepository
            .findByNrInStockGreaterThan(NR_IN_STOCK_THRESHOLD)
            .filter(productStock -> {
                LocalDate outOfStockDate = getLocalDate(productStock.getProjectedOutOfStockDate());
                long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), outOfStockDate);
                return daysBetween > OUT_OF_STOCK_DATE_WINDOW;
            })
            .forEach(productStock -> {
                productStock.setEligableForPromotion(true);
                productStockRepository.save(productStock);
                LOG.debug("Setting eligable for promotion: {}", productStock.getName());
            });
    }

    /**
     * Get java8 LocalDate by java.util.Date.
     * @param date the date
     */
    private LocalDate getLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
