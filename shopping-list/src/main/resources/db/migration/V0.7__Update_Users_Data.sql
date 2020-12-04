update users set password = '$2y$12$BeyP6h42OrfK6Vw.JDjlIO1yC2oYGzHz4vrm3aNWvTq1SaBEBLjPm'
where enabled = true;

INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_USER');