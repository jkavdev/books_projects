package br.com.jkavdev.jpaeficaz.testes;

import javax.persistence.EntityManager;

import br.com.jkavdev.jpaeficaz.modelo.heranca.joined.PessoaFisica;
import br.com.jkavdev.jpaeficaz.modelo.heranca.joined.PessoaJuridica;
import br.com.jkavdev.jpaeficaz.modelo.heranca.singtable.Funcionario;
import br.com.jkavdev.jpaeficaz.modelo.heranca.singtable.Motorista;
import br.com.jkavdev.jpaeficaz.modelo.heranca.tabperlcass.Aviao;
import br.com.jkavdev.jpaeficaz.modelo.heranca.tabperlcass.Carro;
import br.com.jkavdev.jpaeficaz.util.JpaUtil;

public class HerancaTest {

	public static void main(String[] args) {

		EntityManager manager = JpaUtil.geEntityManager();

		Motorista motorista = new Motorista();
		motorista.setNome("Jhonatan");
		motorista.setCnh("5551214");

		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Lucas");
		funcionario.setMatricula("99346");

		PessoaFisica pessoaFisica = new PessoaFisica();
		pessoaFisica.setCpf("04488289100");
		pessoaFisica.setNome("Jhonatan");

		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setCnpj("554555555555555");
		pessoaJuridica.setNome("Padria dos paes");

		Carro carro = new Carro();
		carro.setAno(2015);
		carro.setModelo("Fiat Uno");
		carro.setPortas(4);

		Aviao aviao = new Aviao();
		aviao.setModelo("Boeing");
		aviao.setTipo("Viagem");
		aviao.setPassageiros(900);

		manager.getTransaction().begin();

		manager.persist(aviao);
		manager.persist(carro);
		manager.persist(motorista);
		manager.persist(funcionario);
		manager.persist(pessoaFisica);
		manager.persist(pessoaJuridica);
		manager.getTransaction().commit();

		JpaUtil.close();
	}

}
