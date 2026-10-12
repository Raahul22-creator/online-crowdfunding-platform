package com.crowdfund.servlet;

import com.crowdfund.dao.CampaignDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/contribute")
public class ContributeServlet extends HttpServlet {
    private final CampaignDAO dao = new CampaignDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int campaignId = Integer.parseInt(request.getParameter("campaignId"));
            BigDecimal amount = new BigDecimal(request.getParameter("amount"));
            if (dao.contribute(campaignId, amount)) {
                response.sendRedirect(request.getContextPath() + "/campaigns?message=recorded");
            } else {
                response.sendError(400, "Invalid campaign or contribution amount.");
            }
        } catch (NumberFormatException e) {
            response.sendError(400, "Enter a valid campaign ID and contribution amount.");
        } catch (Exception e) {
            getServletContext().log("Unable to record contribution", e);
            response.sendError(500, "Unable to record contribution.");
        }
    }
}
