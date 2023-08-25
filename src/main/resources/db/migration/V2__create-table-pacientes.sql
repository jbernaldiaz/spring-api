create table pacientes(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    documento varchar(14) not null unique,
    telefono varchar(20) not null,
    calle varchar(100) not null,
    numero varchar(20),
    provincia varchar(100) not null,
    urbanizacion varchar(100) not null,
    codigo varchar(9),
    distrito varchar(100) not null,
    ciudad varchar(100) not null,
    complemento varchar(100),

    primary key(id)

    );