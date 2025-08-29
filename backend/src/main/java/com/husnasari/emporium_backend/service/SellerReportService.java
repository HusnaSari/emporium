package com.husnasari.emporium_backend.service;

import com.husnasari.emporium_backend.model.Seller;
import com.husnasari.emporium_backend.model.SellerReport;

public interface SellerReportService {
    SellerReport getSellerReport(Seller seller);

    SellerReport updateSellerReport(SellerReport sellerReport);
}
