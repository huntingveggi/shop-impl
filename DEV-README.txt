Hier ein paar Hinweise zur Implementierung:

Entities
-------------------------------------------------------------------------------------
Erstellung einer Entity und Nutzung über die aktuelle Implementierung:

... extends AbstractEntity
	--> Da AbtractEntity das Interface Entity implemementiert, 
	ist man gezwungen, getId() zu implementieren. Die eigene Impl muss natürlich auch das
	setId(int id) implementieren, sonst schlägt Hibernate fehl.
	
DAOs
-------------------------------------------------------------------------------------
Erstellung eines Daos:

1. ... extends AbstractDAO
	--> Durch Nutzung des AbstractDAOs wird eine SessionFactory injected und 
	es existieren die methoden
		- protected Session getCurrentSession()
		protected void closeCurrentSession() 
	Diese können nun genutzt werden, um sich eine aktuelle Session zu holen und auf dieser
	entsprechende Transaktionen zu starten und die Persistenzoperationen durchfzuführen.
	
Da jeder DAO eine neue Instanz ist (@Scope("prototype")), wird für jeden 
DAO eine eigene Session geöffnet und diese im jeweiligen DAO gehalten. 
Auch wenn Hibernate effiziente SessionPool-Mechanismen hat, so ists aktuell einfacher dies
zunächst so zu übernehmen.

 
	