## RINHA DE BACKEND - JAVA

Project based on twitter challenge create by: [@zanfranceschi](https://twitter.com/zanfranceschi)

Details on the [challenge repository](https://github.com/zanfranceschi/rinha-de-backend-2023-q3)

### Stack used

- Java 21
- Postgres
- Nginx

### V1

---

Only for test using h2 database \
Total records: 4075

### V2

---

Using select with LIKE \
Total records: 7957

### V3

---

Use gist to search \
Total records: 27018

### How to run

---

Clone the repository

```bash
git clone https://github.com/LuizVictor/rinha-de-backend-java.git
```

Build the application with docker

```bash
docker compose up --build
```

Enter on stress test folder

```bash
cd stress-test
```

Run the stress test

```bash
./run-test.sh
```

