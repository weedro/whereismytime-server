# where is my time? :c

| method | path | describe |
| ------ | ---- | -------- |
|GET     | /api/v1/track/{userId} | get all time updates of user
| POST   | /api/v1/track | save new time update of user

```json
[
  {
    "userId": "userId",
    "windowName": "windowName",
    "wastedTime": 123321
  }
]
```