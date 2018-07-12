-- @formatter:off
-- clientes
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Ricard', 'Pepe', 'abc@gmail.com', '2018-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Juancho', 'Juano', 'aasc@gmail.com', '2013-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Martin', 'Pepe', 'abc@gmail.com', '2018-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Pepe', 'Juano', 'aasc@gmail.com', '2013-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Eva', 'Pepe', 'abc@gmail.com', '2018-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Laura', 'Juano', 'aasc@gmail.com', '2013-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Emilio', 'Pepe', 'abc@gmail.com', '2018-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Carlos', 'Juano', 'aasc@gmail.com', '2013-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Ricard', 'Pepe', 'abc@gmail.com', '2018-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Pancho', 'Juano', 'aasc@gmail.com', '2013-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Alfredo', 'Pepe', 'abc@gmail.com', '2018-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Jacinto', 'Juano', 'aasc@gmail.com', '2013-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Luz', 'Pepe', 'abc@gmail.com', '2018-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Sergio', 'Juano', 'aasc@gmail.com', '2013-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Jose', 'Pepe', 'abc@gmail.com', '2018-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Maria', 'Juano', 'aasc@gmail.com', '2013-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Emma', 'Pepe', 'abc@gmail.com', '2018-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Alba', 'Juano', 'aasc@gmail.com', '2013-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('David', 'Pepe', 'abc@gmail.com', '2018-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Alex', 'Juano', 'aasc@gmail.com', '2013-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Eric', 'Pepe', 'abc@gmail.com', '2018-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Manuel', 'Juano', 'aasc@gmail.com', '2013-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Javier', 'Pepe', 'abc@gmail.com', '2018-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Leo', 'Juano', 'aasc@gmail.com', '2013-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Ernesto', 'Pepe', 'abc@gmail.com', '2018-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Rafa', 'Juano', 'aasc@gmail.com', '2013-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Ricard', 'Pepe', 'abc@gmail.com', '2018-03-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto)
VALUES ('Pablo', 'Juano', 'aasc@gmail.com', '2013-03-10', '');

-- productos

INSERT INTO productos (nombre, precio, create_at) VALUES ('TV Sony 50 inch', 520, '2018-02-16');
INSERT INTO productos (nombre, precio, create_at) VALUES ('Tostadora Sharp 33W', 110, '2018-03-16');
INSERT INTO productos (nombre, precio, create_at) VALUES ('Altavoces subwofer 12 W Phillips', 80, '2018-05-26');
INSERT INTO productos (nombre, precio, create_at) VALUES ('TV LG 43 inch LED', 270, '2018-01-26');
INSERT INTO productos (nombre, precio, create_at) VALUES ('iMac 27 inch i7 retina', 1270, '2018-04-11');
INSERT INTO productos (nombre, precio, create_at) VALUES ('Camera Reflex Canono 21 MP', 770, '2018-01-13');

-- facturas

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at)
VALUES ('Factura de prueba 1', 'Observaciones de la factura 1', 1, NOW());
INSERT INTO facturas_items (cantidad, producto_id, factura_id) VALUES (2, 1, 1);
INSERT INTO facturas_items (cantidad, producto_id, factura_id) VALUES (1, 2, 1);
INSERT INTO facturas_items (cantidad, producto_id, factura_id) VALUES (3, 3, 1);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at)
VALUES ('Factura de prueba 2', 'Observaciones de la factura 2', 1, NOW());
INSERT INTO facturas_items (cantidad, producto_id, factura_id) VALUES (2, 1, 2);
INSERT INTO facturas_items (cantidad, producto_id, factura_id) VALUES (1, 2, 2);
INSERT INTO facturas_items (cantidad, producto_id, factura_id) VALUES (3, 3, 2);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at)
VALUES ('Factura de prueba 3', 'Observaciones de la factura 3', 2, NOW());
INSERT INTO facturas_items (cantidad, producto_id, factura_id) VALUES (2, 1, 3);
INSERT INTO facturas_items (cantidad, producto_id, factura_id) VALUES (2, 2, 3);
INSERT INTO facturas_items (cantidad, producto_id, factura_id) VALUES (5, 4, 3);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at)
VALUES ('Factura de prueba 4', 'Observaciones de la factura 4', 2, NOW());
INSERT INTO facturas_items (cantidad, producto_id, factura_id) VALUES (2, 1, 4);
INSERT INTO facturas_items (cantidad, producto_id, factura_id) VALUES (2, 2, 4);
INSERT INTO facturas_items (cantidad, producto_id, factura_id) VALUES (5, 4, 4);

-- @formatter:on

