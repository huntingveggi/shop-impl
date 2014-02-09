Hier ein paar Hinweise zur Implementierung:

Entities
-------------------------------------------------------------------------------------
Erstellung einer Entity und Nutzung �ber die aktuelle Implementierung:

... extends AbstractEntity
	--> Da AbtractEntity das Interface Entity implemementiert, 
	ist man gezwungen, getId() zu implementieren. Die eigene Impl muss nat�rlich auch das
	setId(int id) implementieren, sonst schl�gt Hibernate fehl.
	
DAOs
-------------------------------------------------------------------------------------
Erstellung eines Daos:

1. ... extends AbstractDAO
	--> Durch Nutzung des AbstractDAOs wird eine SessionFactory injected und 
	es existieren die methoden
		- protected Session getCurrentSession()
		protected void closeCurrentSession() 
	Diese k�nnen nun genutzt werden, um sich eine aktuelle Session zu holen und auf dieser
	entsprechende Transaktionen zu starten und die Persistenzoperationen durchfzuf�hren.
	
Da jeder DAO eine neue Instanz ist (@Scope("prototype")), wird f�r jeden 
DAO eine eigene Session ge�ffnet und diese im jeweiligen DAO gehalten. 
Auch wenn Hibernate effiziente SessionPool-Mechanismen hat, so ists aktuell einfacher dies
zun�chst so zu �bernehmen.

 
	