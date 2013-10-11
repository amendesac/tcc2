package algoritimoGenetico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import models.Alocacao;
import models.Disciplina;
import models.Horario;
import models.Parametros;
import models.Sala;

public class Populacao {

	public List<Individuo> populacao = new ArrayList<Individuo>();

	private Parametros parametros;

	public Populacao(Parametros p) {

		parametros = p;

		populacao = new ArrayList<Individuo>();

	}

	public void populacaoInicial(int tamanhoPopulacao) {

		for (int i = 0; i < tamanhoPopulacao; i++) {

			Individuo individuo = new Individuo(parametros);

			individuo.populate();

			populacao.add(individuo);

		}

	}

	public Individuo melhor() {

		Individuo melhor = populacao.get(0);

		return melhor;

	}

	public boolean temSolucao(Individuo i) {

		if (i.fitness > 50)
			return true;

		return false;
	}

	// ordena a população pelo valor de aptidão de cada indivíduo, do maior
	// valor para o menor, assim se eu quiser obter o melhor indivíduo desta
	// população, acesso a posição 0 do array de indivíduos
	public void ordenar() {

		Collections.sort(populacao, new Comparator() {
			public int compare(Object o1, Object o2) {
				Individuo i1 = (Individuo) o1;
				Individuo i2 = (Individuo) o2;
				return i1.fitness.compareTo(i2.fitness)*-1;
			}
		});

	}

	public void alocar(Individuo i) {

		for (Gene g : i.cromossomo) {

			Alocacao a = new Alocacao();

			a.sala = Sala.findById(g.sala.id);

			a.horario = Horario.findById(g.horario.id);

			a.dia = g.diaSemana;

			if (g.disciplinaHorario == null)

				a.disciplina = null;
			else
				a.disciplina = Disciplina.findById(g.disciplinaHorario.disciplina.id);

			a.save();

		}

	}
}