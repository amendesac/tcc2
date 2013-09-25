package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import play.db.jpa.GenericModel;

/**
 * @author Alexandre Gonzaga Mendes 25/09/2013
 */

@Entity
@Table(name = "disciplina", schema = "public")
public class Disciplina extends GenericModel {

	@Id
	@Column(name = "id_disciplina")
	@GeneratedValue(generator = "disciplina_id_disciplina_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "disciplina_id_disciplina_seq", sequenceName = "disciplina_id_disciplina_seq")
	public Integer id;

	@ManyToOne
	@JoinColumn(name = "id_periodo")
	public Periodo periodo;

	@Column(name = "numero_vagas")
	public Integer numeroVagas;

	@Column(name = "texto_codigo")
	public String textoCodigo;

	@Column(name = "nome_disciplina")
	public String nomeDisciplina;

	@Column(name = "texto_turma")
	public String textoTurma;

}
