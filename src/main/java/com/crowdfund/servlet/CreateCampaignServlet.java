package com.crowdfund.servlet;

import com.crowdfund.dao.CampaignDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/create-campaign")
public class CreateCampaignServlet extends HttpServlet {
    private final CampaignDAO dao = new CampaignDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String creator = request.getParameter("creatorName");
        try {
            BigDecimal target = new BigDecimal(request.getParameter("targetAmount"));
            if (title == null || title.isBlank() || description == null || description.isBlank()
                    || creator == null || creator.isBlank() || target.compareTo(BigDecimal.ZERO) <= 0) {
                response.sendError(400, "Please enter valid campaign details.");
                return;
            }
            dao.createCampaign(title.trim(), description.trim(), creator.trim(), target);
            response.sendRedirect(request.getContextPath() + "/campaign-submitted.jsp");
        } catch (NumberFormatException e) {
            response.sendError(400, "Target amount must be a valid number.");
        } catch (Exception e) {
            getServletContext().log("Unable to create campaign", e);
            response.sendError(500, "Unable to save campaign.");
        }
    }
}
