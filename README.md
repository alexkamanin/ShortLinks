# Short Link Service

### How it started

In between android work wrote a small service for shortening links.

### Technologies stack

- Kotlin
- Spring Boot
- Paging
- PostgreSQL
- Swagger
- Gradle

### Guide
1. First, you need to send the original link in the request body to [http://localhost:5000/links]()

```json
{
  "original": "https://www.jetbrains.com/ru-ru/opensource/kotlin/"
}
```

2. In response, you will receive a short link:

```json
{
  "link": "8cc91d52"
}
```
3. Now, in any browser, insert the required part of the address and the resulting short link into the address bar:
[http://localhost:5000/links/{linkId}](). Congratulations, you have been successfully redirected.  
4. To get statistics from this short link, make a request: [http://localhost:5000/statistics/{linkId}]()
```json
{
  "link": "8cc91d52",
  "original": "https://www.jetbrains.com/ru-ru/opensource/kotlin/",
  "rank": 1,
  "count": 9854
}
```
5. If you want to get statistics on all sites, specify the required page and the number of entries on it (by default, page = 1, count = 10) [http://localhost:5000/statistics?count=10&page=1]().
6. If something goes wrong, you will be notified with an approximate error:
```json
{
    "timestamp": "31.12.2021 23:59:59",
    "code": 404,
    "error": "Not Found",
    "message": "Not found original by link = 8cc91d52"
}
```