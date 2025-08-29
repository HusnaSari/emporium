package com.husnasari.emporium_backend.service.impl;

import org.springframework.stereotype.Service;

import com.husnasari.emporium_backend.model.Seller;
import com.husnasari.emporium_backend.model.SellerReport;
import com.husnasari.emporium_backend.repository.SellerReportRepository;
import com.husnasari.emporium_backend.service.SellerReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerReportServiceImpl implements SellerReportService {

    private final SellerReportRepository sellerReportRepository;

    @Override
    public SellerReport getSellerReport(Seller seller) {
        SellerReport sr = sellerReportRepository.findBySellerId(seller.getId());

        if (sr == null) {
            SellerReport newReport = new SellerReport();
            newReport.setSeller(seller);
            return sellerReportRepository.save(newReport);
        }
        return sr;
    }

    @Override
    public SellerReport updateSellerReport(SellerReport sellerReport) {

        return sellerReportRepository.save(sellerReport);
    }

}
