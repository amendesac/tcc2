package algoritimoGenetico;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.Disciplina;
import models.DisciplinaHorario;
import models.Horario;
import models.Parametros;
import models.Sala;

public class Individuo implements Cloneable {

	private List<Gene> cromossomo = null;

	private Float fitness = null;

	private String genoma = null;

	private Parametros parametros;

	public Individuo(Individuo individuo) throws CloneNotSupportedException {

		this.cromossomo = new ArrayList<Gene>();

		for (int i = 0; i < individuo.getCromossomo().size(); i++) {
			this.cromossomo.add((Gene) individuo.getCromossomoPosition(i).clone());
		}

		this.parametros = individuo.parametros;

		fitness();

	}

	// construtor padrao
	public Individuo(Parametros p) {

		this.parametros = p;

		create();

	}// fim método Individuo

	// cria um indviduo sem nenhum horarioDisciplina alocado para o mesmo e seta
	// o fitness como 0 pois não existe nenhum parametro ainda para ser
	// calculado
	public void create() {

		this.cromossomo = new ArrayList<Gene>();

		for (Sala sala : parametros.listSalas) {

			for (int dia = 1; dia < 8; dia++) {

				for (Horario horario : parametros.listHorarios) {

					Gene g = new Gene(sala, dia, horario, null);

					this.cromossomo.add(g);

				}// fim for horarios

			}// fim for dias

		}// fim for salas

		fitness = 0f;

	}

	// faz uma alocação de todas horarioDisciplina de uma forma randomica
	public void populate() {

		for (int i = 0; i < parametros.listHorarioDisciplina.size(); i++) {

			this.cromossomo.get(i).setDisciplinaHorario(parametros.listHorarioDisciplina.get(i));
		}

		fitness();

	}

	// calcula a apitidao do individuo
	private void fitness() {

		int countTotalAlocados = 0;

		float fitness01 = 0;

		for (Gene g : cromossomo) {

			if (g.getDisciplinaHorario() != null) {

				if ((g.getHorario().id == g.getDisciplinaHorario().horario.id) && (g.getDiaSemana() == g.getDisciplinaHorario().dia)) {

					countTotalAlocados++;

				}

			}

		}

		if (countTotalAlocados == 0)

			fitness01 = 0;

		else

			fitness01 = (countTotalAlocados * 100) / parametros.listHorarioDisciplina.size();

		// penalidade para disciplinas com alocação indevida

		// for (Disciplina d : parametros.listDisciplinas) {
		//
		// countNecessario = 0;
		//
		// countTotalAlocados = 0;
		//
		// for (DisciplinaHorario hp : parametros.listHorarioDisciplina) {
		//
		// if (hp.disciplina.id == d.id)
		//
		// countNecessario++;
		//
		// }
		//
		// for (Gene g : cromossomo) {
		//
		// if ((g.disciplinaHorario != null) &&
		// (g.disciplinaHorario.disciplina.id == d.id))
		//
		// countTotalAlocados++;
		// }
		//
		// if (countTotalAlocados > countNecessario) {
		// fitness02 -= (parametros.listHorarioDisciplina.size() * 100) /
		// (countTotalAlocados - countNecessario);
		// }
		//
		// }

		// penalidades

		this.fitness = fitness01;

		print();

	}

	// imprime o genoma do individuo
	public void print() {

		this.genoma = "";

		for (Gene g : cromossomo) {

			if (g.getDisciplinaHorario() == null)

				this.genoma += "0";

			else

				this.genoma += "1";

		}

	}// fim método print

	public List<Gene> getCromossomo() {

		return this.cromossomo;

	}

	public Float getFitness() {

		return this.fitness;

	}

	public String getGenoma() {

		return this.genoma;

	}

	public Gene getCromossomoPosition(Integer i) {

		return this.cromossomo.get(i);

	}

	public void removeCromossomoPosition(int i) {

		this.cromossomo.remove(i);

	}

	public void setHorarioDiscplina(Gene g, Integer i) {

		this.cromossomo.get(i).setDisciplinaHorario(g.getDisciplinaHorario());

	}

	@Override
	protected Object clone() throws CloneNotSupportedException {

		return super.clone();

	}

}
