CREATE DATABASE IF NOT EXISTS crowdfunding_db;
USE crowdfunding_db;

CREATE TABLE IF NOT EXISTS campaigns (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(120) NOT NULL,
    description TEXT NOT NULL,
    creator_name VARCHAR(80) NOT NULL,
    target_amount DECIMAL(12,2) NOT NULL,
    raised_amount DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS contributions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    campaign_id INT NOT NULL,
    amount DECIMAL(12,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_contribution_campaign FOREIGN KEY (campaign_id) REFERENCES campaigns(id)
);

-- Optional test record for checking the campaign list:
INSERT INTO campaigns (title, description, creator_name, target_amount, status)
VALUES ('Community Library', 'A sample campaign to create a small community library.', 'Demo Creator', 50000.00, 'APPROVED');
