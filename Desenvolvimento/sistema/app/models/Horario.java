package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import play.db.jpa.GenericModel;

/**
 * @author Alexandre Gonzaga Mendes 25/09/2013
 */

@Entity
@Table(name = "horario", schema = "public")
public class Horario extends GenericModel {

	@Id
	@Column(name = "id_horario")
	@GeneratedValue(generator = "horario_id_horario_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "horario_id_horario_seq", sequenceName = "horario_id_horario_seq")
	public Integer id;

	@ManyToOne
	@JoinColumn(name = "id_turno")
	public Turno turno;

	
//	@Column(name="horario_de", nullable = false)
//	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//	public LocalDateTime horarioDe;
//	
//	@Column(name="horario_ate", nullable = false)
//	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//	public LocalDateTime horarioAte;
	//
	
	@Column(name = "horario_de")
	public Date horarioDe;

	@Column(name = "horario_ate")
	public Date horarioAte;

}
