# APIs
(* mandatory parameter)

(@ parameters should be enchrypted using "multipart/form-data")

(^ can be performed only by registered users that owns a *token*)
### Users
| Link | Method | Description | Parameters |
| ------ | ------ | ------ | ------ |
| http://localhost:8080/BroadGamesREST/jaxrs/api/users | GET | list of all users | |
|                                                      | POST | create new user  |  name* , address, phoneNumber, password* |
| http://localhost:8080/BroadGamesREST/jaxrs/api/users/{id} | GET |   view info user {id} | |
|                                                           | DELETE | delete user {id}| |
|                                                           | PUT  | update user {id}  | name, address, phoneNumber|
| http://localhost:8080/BroadGamesREST/jaxrs/api/users/search/ | POST | return list of filtered and ordered users | name, address, phoneNumber, id, orderAttribute ("name", "phoneNumber", "address", "id"), descending ("true", "false") |

### Board Games
| Link | Method | Description | Parameters |
| ------ | ------ | ------ | ------ |
| http://localhost:8080/BroadGamesREST/jaxrs/api/boardGames/ |  GET   |  list of all games | |
|                                                            | POST @^ |  create new game  | name* , token* , designers(comma-separated list), image | 
| http://localhost:8080/BroadGamesREST/jaxrs/api/boardGames/{id} | GET | view info game {id} | |
|                                                                | DELETE | delete game {id} | |
| http://localhost:8080/BroadGamesREST/jaxrs/api/boardGames/{id}/cover | GET | download cover image of the game | |
| http://localhost:8080/BroadGamesREST/jaxrs/api/boardGames/search/ | POST |  return list of filtered and ordered games  | name, designers(comma-separated list), id, orderAttribute ("name", "id"), descending ("true", "false") |

### Plays
| Link | Method | Description | Parameters |
| ------ | ------ | ------ | ------ |
| http://localhost:8080/BroadGamesREST/jaxrs/api/plays/ | POST ^ | create play	|	userId* , gameId* , date* , token* , time, players, winnerId |
| http://localhost:8080/BroadGamesREST/jaxrs/api/plays/{userId} | GET	|	list of plays of the user {userId} ||
| http://localhost:8080/BroadGamesREST/jaxrs/api/plays/search/ | POST | return list of filtered and ordered plays | userId, gameId, date, orderAttribute ("userId", "gameId", "date", "numPlayers"), descending ("true", "false")|

### Authentication
| Link | Method | Description | Parameters |
| ------ | ------ | ------ | ------ |
| http://localhost:8080/BroadGamesREST/jaxrs/auth/ | POST |	authentication function that return a *token* to be used for creating resources	|	username* , password* |

