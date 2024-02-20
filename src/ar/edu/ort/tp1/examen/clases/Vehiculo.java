/**
 * 
 */
package ar.edu.ort.tp1.examen.clases;

/**
 * Vehículos estacionables en un estacionamiento
 */
public abstract class Vehiculo {

	// TODO: COMPLETAR

	/**
	 * Constructor del vehículo
	 * 
	 * @param patente       Patente
	 * @param horaIngreso   Hora del horario de ingreso
	 * @param minutoIngreso minutos del horario de ingreso
	 */
	
	private String patente; 
	private Hora  horaIngreso;
	
	public Vehiculo(String patente, Hora horaIngreso) {
		setPatente(patente);
		setHoraIngreso(horaIngreso);
	}

	public abstract float calcularImporte(Hora horaEgreso);
	public abstract void validarPatente(String patente);

	public String getPatente() {
		return patente;
	}
	
	private void setPatente(String patente) {
		this.patente = patente;
	}

	public Hora getHoraIngreso() {
		return horaIngreso;
	}

	private void setHoraIngreso(Hora horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	
	/**
	 * @param horasEgreso
	 * @param minutosEgreso
	 */
	public void validarHoraEgreso(Hora hora) throws EstacionamientoException {

		if (!hora.esValida()) {
			throw new EstacionamientoException("Horario de egreso inválido");
		}
		
		if (!horaIngreso.esAnterior(hora)) {
			throw new EstacionamientoException("Horario de egreso anterior al de ingreso");
		}

	}

	@Override
	public String toString() {
		return "Vehiculo [patente=" + patente + ", horaIngreso=" + horaIngreso + "]";
	}

}
