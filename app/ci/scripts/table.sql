CREATE TABLE saga_sale.sales (
    id INT not null auto_increment,
    product_id INT not null,
    user_id INT not null,
    value decimal,
    status_id INT not null,
    quantity INT,
    primary key(id)
);
CREATE TABLE saga_inventory.inventories (
    id INT not null auto_increment,
    product_id INT not null,
    quantity INT,
    primary key(id)
);
CREATE TABLE saga_payment.payments (
    id INT not null auto_increment,
    user_id INT not null,
    sale_id INT not null,
    value decimal,
    primary key(id)
);
CREATE TABLE saga_payment.users (
    id INT not null auto_increment,
    name varchar(50) not null,
    balance decimal,
    primary key(id)
);