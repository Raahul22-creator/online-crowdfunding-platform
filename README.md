# FundForward — Java Web-Based Crowdfunding Platform

## Technology
- Java 17+
- JSP, HTML, CSS
- Java Servlets (Jakarta Servlet 6)
- MySQL with JDBC
- Maven
- Apache Tomcat 10.1+

## Database setup
1. Install and start MySQL.
2. Run `database/schema.sql` in MySQL Workbench or the MySQL command line.
3. Configure environment variables if needed:
   - `DB_URL` (default `jdbc:mysql://localhost:3306/crowdfunding_db`)
   - `DB_USER` (default `root`)
   - `DB_PASSWORD` (default empty; set your actual local password outside source code)
4. Do not upload passwords to GitHub.

## Build and run
1. Install JDK 17+, Maven, and Tomcat 10.1+.
2. From the project root, run `mvn clean package`.
3. Copy `target/crowdfunding.war` into Tomcat's `webapps` directory.
4. Start Tomcat and open `http://localhost:8080/crowdfunding/`.

## Included scope
- Home page and create-campaign form.
- Java Servlet for creating campaigns.
- DAO class using JDBC `PreparedStatement`.
- Database connection utility using `DriverManager`.
- Campaign listing for approved campaigns.
- Contribution records and raised-amount updates in a database transaction.
- Basic validation and exception handling.

## Review 1 status
This is a starter implementation and must be run/tested in your environment before submission. Capture real screenshots of the running application and add them to `screenshots/`. Admin login/approval interface and full authentication are not included in this Review 1 starter.
