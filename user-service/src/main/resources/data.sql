DROP TABLE IF EXISTS users;

CREATE TABLE users(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(20),
    lastname VARCHAR(20),
    username VARCHAR(20) NOT NULL,
    email VARCHAR(20) UNIQUE,
    password varchar(100) NOT NULL
);

INSERT INTO users (name, lastname, username, password, email) VALUES                ( 'Admin', 'Test', 'adminuser', 'samplE_12345' , 'admin@mail.com' ),
                                                                                    ( 'Selim', 'Canlı', 'basic', 'samplE_12345' , 'selim2@mail.com' ),
                                                                                    ( 'Mehmet', 'Kaya', 'basic', 'samplE_12345' , 'mehmet@mail.com' ),
                                                                                    ( 'Erdem', 'Canlı', 'basic', 'samplE_12345' , 'erdemm@mail.com' ),
                                                                                    ( 'Cengiz', 'Arf', 'basic', 'samplE_12345' , 'cengiz@mail.com' ),
                                                                                    ( 'Ferhat', 'Tapas', 'basic', 'samplE_12345' , 'ferrh@mail.com' ),
                                                                                    ( 'Selim', 'Asaf', 'basic', 'samplE_12345' , 'selim@mail.com' ),
                                                                                    ( 'Hekim', 'Gandas', 'basic', 'samplE_12345' , 'gandas@mail.com' ),
                                                                                    ( 'Tekin', 'Karlı', 'basic', 'samplE_12345' , 'tekin@mail.com' ),
                                                                                    ( 'Kamil', 'Peksimet', 'basic', 'samplE_12345' , 'ridvan@mail.com' );