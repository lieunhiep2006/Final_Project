package com.bakershop.controller;

import com.bakershop.dao.VoucherDAO;
import com.bakershop.model.Voucher;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/admin/manage-vouchers", "/admin/manage-vouchers/add", "/admin/manage-vouchers/delete"})
public class VoucherController extends HttpServlet {
    private VoucherDAO voucherDAO;

    @Override
    public void init() throws ServletException {
        this.voucherDAO = new VoucherDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        
        if ("/admin/manage-vouchers/delete".equals(path)) {
            try {
                long id = Long.parseLong(request.getParameter("id"));
                voucherDAO.deleteVoucher(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect(request.getContextPath() + "/admin/manage-vouchers");
            return;
        }

        List<Voucher> vouchers = voucherDAO.getAllVouchers();
        request.setAttribute("vouchers", vouchers);
        request.getRequestDispatcher("/WEB-INF/views/admin/manage-vouchers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();

        if ("/admin/manage-vouchers/add".equals(path)) {
            try {
                String code = request.getParameter("code");
                String discountType = request.getParameter("discountType");
                double discountValue = Double.parseDouble(request.getParameter("discountValue"));
                
                String minOrderStr = request.getParameter("minOrderAmount");
                Double minOrderAmount = (minOrderStr != null && !minOrderStr.trim().isEmpty()) ? Double.parseDouble(minOrderStr) : 0.0;

                String maxDiscountStr = request.getParameter("maxDiscountAmount");
                Double maxDiscountAmount = (maxDiscountStr != null && !maxDiscountStr.trim().isEmpty()) ? Double.parseDouble(maxDiscountStr) : null;

                String usageLimitStr = request.getParameter("usageLimit");
                Integer usageLimit = (usageLimitStr != null && !usageLimitStr.trim().isEmpty()) ? Integer.parseInt(usageLimitStr) : null;

                Date startDate = Date.valueOf(request.getParameter("startDate"));
                Date endDate = Date.valueOf(request.getParameter("endDate"));

                Voucher v = new Voucher();
                v.setCode(code);
                v.setDiscountType(discountType);
                v.setDiscountValue(discountValue);
                v.setMinOrderAmount(minOrderAmount);
                v.setMaxDiscountAmount(maxDiscountAmount);
                v.setUsageLimit(usageLimit);
                v.setStartDate(startDate);
                v.setEndDate(endDate);

                voucherDAO.addVoucher(v);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect(request.getContextPath() + "/admin/manage-vouchers");
        }
    }
}