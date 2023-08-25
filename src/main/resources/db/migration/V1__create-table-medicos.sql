create table medicos(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    documento varchar(6) not null unique,
    telefono varchar(20) not null,
    especialidad varchar(100) not null,
    calle varchar(100) not null,
    numero varchar(20),
    provincia varchar(100) not null,
    urbanizacion varchar(100) not null,
    codigo varchar(9) not null,
    distrito varchar(100) not null,
    ciudad varchar(100) not null,
    complemento varchar(100),

    primary key (id));
)