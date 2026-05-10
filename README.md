Para formatear recuento de ids(ejecutar en consulta de Laragon) 

SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE imagen;
TRUNCATE TABLE detalle_boleta;
TRUNCATE TABLE boleta;
TRUNCATE TABLE usuario;
TRUNCATE TABLE producto;
TRUNCATE TABLE productos;
TRUNCATE TABLE categoria;
TRUNCATE TABLE categorias;
TRUNCATE TABLE marca;
TRUNCATE TABLE marcas;
TRUNCATE TABLE metodo_envio;
TRUNCATE TABLE metodo_pago;
TRUNCATE TABLE comuna;
TRUNCATE TABLE region;

SET FOREIGN_KEY_CHECKS = 1;
