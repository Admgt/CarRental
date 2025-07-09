# Autókölcsönző Alkalmazás

Ez egy egyszerű, webes autókölcsönző alkalmazás, amely Spring Boot keretrendszerrel készült. A projekt lehetővé teszi a felhasználók számára, hogy szabad autókat keressenek egy adott időszakra, és lefoglalják azokat. Emellett rendelkezik egy adminisztrációs felülettel is a foglalások megtekintésére és az autók kezelésére.

---

## Főbb Funkciók

### Publikus Felület
*   **Autókeresés:** Dátumtartomány alapján elérhető, szabad autók listázása képpel és napi árral.
*   **Foglalás:** Felhasználói adatok megadásával lehetőség van a kiválasztott autó lefoglalására.

### Adminisztrációs Felület
*   **Bejelentkezés:** Az admin felület (`/admin`) jelszóval védett, a belépési adatok a konfigurációs fájlból származnak.
*   **Foglalások listázása:** Az összes beérkezett foglalás megtekintése egy táblázatos nézetben.
*   **Autók kezelése:**
    *   Új autók felvitele.
    *   Meglévő autók adatainak szerkesztése (márka, modell, ár, kép URL).
    *   Autók ideiglenes deaktiválása (törlés helyett).

---

## Használt Technológiák

Ez a projekt a következő technológiákra és könyvtárakra épül:

### Backend
*   **Java 21**
*   **Spring Boot 3.5.3**
    *   **Spring Web**
    *   **Spring Data JPA**
    *   **Spring Security**
*   **Hibernate**
*   **H2 Database Engine**
*   **Maven**

### Frontend
*   **Thymeleaf**
*   **HTML5**
*   **Bootstrap 5**

---

## Böngészőben az elérési útvonalak

*   **Publikus felület:** `http://localhost:8080`
    *   **H2 Adatbázis Konzol:** `http://localhost:8080/h2-console`
        *   **JDBC URL:** `jdbc:h2:mem:autokolcsonzodb`
        *   **Felhasználónév:** `sa`
        *   **Jelszó:** (hagyd üresen)
    *   **Admin felület:** `http://localhost:8080/admin/cars`
        *   **Felhasználónév:** `admin`
        *   **Jelszó:** `adminpass`