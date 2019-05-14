# Juego de SpaceInvaders 

Space invaders es una modificación del juego del siguiente repositorio: https://github.com/gerardfp/invaders

Tal modificación es un ejercicio realizado en clase, donde había que añadirle nuevas mejoras al videojuego.
Las mejoras han sido las siguientes:

- Pantalla inicial con menú de navegación. 
	 * (Nueva partida, Top10 SpaceInvaders, Configuración y Salir).
	 * Música de menú inicial ejecutada desde un Thread.

- Nueva Partida 
	* Inicia el juego de spaceInavaders.
	* Tenemos 5 vidas, que se restan al recibir un disparo. (También emitimos un sonido al recibir un disparo)
	* Al matar un alien aumenta el contador de muertes de aliens.
	* Posibilidad de morir, se acaba la partida y vamos a ResumeMatchScreen.
	* Posibilidad de derrotar a todos, y vamos a ResumeMatchScreen.
	* Música añadida mientras jugamos.
	* Posibilidad de Silenciar tanto sonidos como música.
	* "Hud" renderizado donde se ve vidas, configuración de sonido y puntuación.
	
- ResumeMatchScreen
	* Inserta en la base de datos externa el resultado de la partida. (Tarda un montón, porque el hosting es malisimo, y solo tienen accesos las ip's indicadas en el hosting)
	* Muestra el resumen de la partida.
	* Vuelve a sonar la música de menús.
	* Posibilidad de volver a menú principal o ir al TOP10.
	
- Top10Screen
	* Nos muestra el top10 de SpaceInvaders de todos los jugadores que han logrado subir su puntuación a la base de datos online. (También tarda un cojon y medio)
	* Si no conseguimos conectarnos a la BD, nos muestra un error indicado que no ha sido posible conectarse a la BD.
	* Vuelves al menú inicial.
	
- Configuración
	* Capacidad de cambiar el nombre del jugador. (Por defecto es player)
	* Capacidad de modificar el volumen de la música. (Afecta en vivo, gracias al Thread)
	* Capacidad de silenciar la musica y los sonidos. (Estos serán hereados en todo el juego)
