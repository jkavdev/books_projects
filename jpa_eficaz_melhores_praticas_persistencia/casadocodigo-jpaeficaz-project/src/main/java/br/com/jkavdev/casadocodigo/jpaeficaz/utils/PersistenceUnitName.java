package br.com.jkavdev.casadocodigo.jpaeficaz.utils;

public enum PersistenceUnitName {

	JPA_EFICAZ_PU("jpaeficazPU"),
	JPA_EFICAZ_OUTRO_PU("jpaeficazOutroPU"),
	JPA_EFICAZ_POSTGRES_PU("jpaeficazPostgresPU");

	private String persistenceUnitName;

	private PersistenceUnitName(String persistenceUnitName) {
		this.persistenceUnitName = persistenceUnitName;
	}

	public String getPersistenceUnitName() {
		return persistenceUnitName;
	}

}
