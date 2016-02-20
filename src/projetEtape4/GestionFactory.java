package projetEtape4;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GestionFactory {
	// Données utiles à la persistance
	private static final String PERSISTENCE_UNIT_NAME = "etape4";
	public static EntityManagerFactory factory;
	
	public static void open() {
		// Creation factory
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	public static void close() {
		// Close factory
		factory.close();
	}

}
