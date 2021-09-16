# where is my time? :c

| method | path | describe |
| ------ | ---- | -------- |
| GET    | /api/v1/track/{userId}?type=summary | get all time updates of user
| GET    | /api/v1/track/{userId}?type=summary | get summary of all time updates of user
| POST   | /api/v1/track | save new time update of user and return count of saved updates

- POST
```json
[
  {
    "userId": "userId",
    "windowName": "windowName",
    "wastedTime": 123321
  }
]
```
- GET
```json
[
  {
    "windowName": "windowName",
    "wastedTime": 123321
  }
]
```
