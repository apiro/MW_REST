(* optional)
http://localhost:8080/BroadGamesREST/jaxrs/api/users
        GET     list of all users
        POST    create new user   -   name, address, phoneNumber 
        		Use: http://localhost:8080/BroadGamesREST/createUser.html 

http://localhost:8080/BroadGamesREST/jaxrs/api/users/{id}
        GET     view info user {id}
        DELETE  delete user {id}
        PUT     update user {id}    -   name, address, phoneNumber

http://localhost:8080/BroadGamesREST/jaxrs/api/users/search/
        POST    return list of filtered users   -   name*, address*, phoneNumber*, id*
        	TODO ordering

http://localhost:8080/BroadGamesREST/jaxrs/api/boardGames/  
        GET     list of all games
        POST    create new game   -   name, designers(comma-separated list), image
        		Use: http://localhost:8080/BroadGamesREST/createGame.html

http://localhost:8080/BroadGamesREST/jaxrs/api/boardGames/{id}
        GET     view info game {id}
        DELETE  delete game {id}

http://localhost:8080/BroadGamesREST/jaxrs/api/boardGames/{id}/cover
		GET		download cover image of the game

http://localhost:8080/BroadGamesREST/jaxrs/api/boardGames/search/
		POST    return list of filtered users   -   name*, designers(comma-separated list)*, id*
			TODO ordering

http://localhost:8080/BroadGamesREST/jaxrs/api/plays/
        POST	create play	 -	userId, gameId, date, time*, players*, winnerId*
        		Use: http://localhost:8080/BroadGamesREST/createPlay.html

http://localhost:8080/BroadGamesREST/jaxrs/api/plays/{userId}
		GET		list of plays of the user	{userId}
			TODO search and ordering


