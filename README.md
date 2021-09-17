# where is my time? :c

## global services url: `localhost:7430`

## authentication service
### url: /aus
| method | path | describe |
| ------ | ---- | -------- |
| POST   | /api/v1/auth | create new user and return token, or return token of already exists user |

- POST
```json
{
  "username": "username",
  "password": "password"
}
```

## where is my time service
### url: /wimt

user token should be placed in `Authorization` header. Example:
`Authorization: Bearer 123123123123123`

| method | path | describe | need user token |
| ------ | ---- | -------- |:---------------:|
| GET    | /api/v1/track/{userId}?type=summary | get all time updates of user | x |
| GET    | /api/v1/track/{userId}?type=summary | get summary of all time updates of user | x |
| POST   | /api/v1/track | save new time update of user and return count of saved updates | x |

- POST
```json
[
  {
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