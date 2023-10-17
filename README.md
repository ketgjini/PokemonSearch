# PokemonSearch
Simple spring boot project calling pokemon API to search for specific pokemons.

Incorporated a Dockerfile and docker-compose in the project.
We use the Dockerfile to create an image of the spring boot application with gradle 7.6.1 and java 17.
We use the docker-compose to create the containers of the spring boot project and mysql database we will be using.
The spring-app depends on the mysql-db, which means that mysql-db has to be created before spring-app.
We have created a mutual network "pokemonapp-net" for both and expose them to port 3308 and 8081 in our local machine.
