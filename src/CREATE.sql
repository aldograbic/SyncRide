CREATE TABLE korisnici (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ime VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    lozinka VARCHAR(255) NOT NULL,
    telefon VARCHAR(15),
    grad_id INT,
    role ENUM('vozac', 'putnik', 'oboje') DEFAULT 'putnik',
    datum_registracije TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (grad_id) REFERENCES gradovi(id) 
);


CREATE TABLE gradovi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ime_grada VARCHAR(100) NOT NULL,
    drzava VARCHAR(100) NOT NULL
);


CREATE TABLE automobili (
    id INT AUTO_INCREMENT PRIMARY KEY,
    korisnik_id INT NOT NULL,
    marka VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    godina_proizvodnje YEAR NOT NULL,
    boja VARCHAR(50),
    broj_sjedala INT NOT NULL,
    registracijska_oznaka VARCHAR(50) NOT NULL,
    FOREIGN KEY (korisnik_id) REFERENCES korisnici(id) ON DELETE CASCADE 
);


CREATE TABLE voznje (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vozac_id INT NOT NULL,
    automobil_id INT,
    pocetna_lokacija VARCHAR(255) NOT NULL,
    krajnja_lokacija VARCHAR(255) NOT NULL,
    vrijeme_polaska DATETIME NOT NULL,
    slobodna_mjesta INT NOT NULL,
    cijena_po_osobi DECIMAL(10, 2),
    dodatne_informacije TEXT,
    FOREIGN KEY (vozac_id) REFERENCES korisnici(id) ON DELETE CASCADE,
    FOREIGN KEY (automobil_id) REFERENCES automobili(id) 
);


CREATE TABLE rezervacije (
    id INT AUTO_INCREMENT PRIMARY KEY,
    putnik_id INT NOT NULL,
    voznja_id INT NOT NULL,
    datum_rezervacije TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('u tijeku', 'potvrdeno', 'otkazano') DEFAULT 'u tijeku',
    FOREIGN KEY (putnik_id) REFERENCES korisnici(id) ON DELETE CASCADE,
    FOREIGN KEY (voznja_id) REFERENCES voznje(id) ON DELETE CASCADE
);


CREATE TABLE poruke (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    voznja_id INT,
    sadrzaj TEXT NOT NULL,
    vrijeme_slanja TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES korisnici(id) ON DELETE CASCADE,
    FOREIGN KEY (receiver_id) REFERENCES korisnici(id) ON DELETE CASCADE,
    FOREIGN KEY (voznja_id) REFERENCES voznje(id) ON DELETE CASCADE
);


CREATE TABLE recenzije (
    id INT AUTO_INCREMENT PRIMARY KEY,
    recenzent_id INT NOT NULL,
    vozac_id INT NOT NULL,
    ocjena INT CHECK(ocjena BETWEEN 1 AND 5),
    komentar TEXT,
    datum_recenzije TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (recenzent_id) REFERENCES korisnici(id), 
    FOREIGN KEY (vozac_id) REFERENCES korisnici(id) 
);


CREATE TABLE notifikacije (
    id INT AUTO_INCREMENT PRIMARY KEY,
    korisnik_id INT NOT NULL,
    poruka TEXT NOT NULL,
    tip_notifikacije ENUM('rezervacija', 'poruka', 'ocjena') NOT NULL,
    vrijeme_notifikacije TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('procitano', 'neprocitano') DEFAULT 'neprocitano',
    FOREIGN KEY (korisnik_id) REFERENCES korisnici(id) ON DELETE CASCADE
);


CREATE TABLE spremljene_voznje (
    id INT AUTO_INCREMENT PRIMARY KEY,
    korisnik_id INT NOT NULL,
    voznja_id INT NOT NULL,
    datum_spremanja TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (korisnik_id) REFERENCES korisnici(id) ON DELETE CASCADE,
    FOREIGN KEY (voznja_id) REFERENCES voznje(id) ON DELETE CASCADE
);
