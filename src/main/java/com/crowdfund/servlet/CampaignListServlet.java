package com.crowdfund.servlet;

import com.crowdfund.dao.CampaignDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/campaigns")
public class CampaignListServlet extends HttpServlet {
    private final CampaignDAO dao = new CampaignDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("campaigns", dao.getApprovedCampaigns());
            request.getRequestDispatcher("/campaigns.jsp").forward(request, response);
        } catch (Exception e) {
            getServletContext().log("Unable to load campaigns", e);
            response.sendError(500, "Could not load campaigns. Check database configuration.");
        }
    }
}
