/**
 * 
 */
package ar.edu.ort.tp1.examen;

import java.util.ArrayList;

import ar.edu.ort.tp1.examen.clases.Estacionamiento;
import ar.edu.ort.tp1.examen.clases.Hora;
import ar.edu.ort.tp1.examen.clases.TipoVehiculo;

public class Test {

	private static final int PRECIO_MOTOCICLETAS_POR_HORA = 30;
	private static final int PRECIO_AUTOS_POR_HORA = 60;
	private static final int CAPACIDAD_MOTOCICLETAS = 5;
	private static final int CAPACIDAD_AUTOS = 6;

	public static void main(String[] args) {

		Test test = new Test();

		Estacionamiento e = new Estacionamiento(CAPACIDAD_AUTOS, CAPACIDAD_MOTOCICLETAS, PRECIO_AUTOS_POR_HORA,
				PRECIO_MOTOCICLETAS_POR_HORA);

		test.estacionarAutos(e);

		System.out.println("-------------------------------------------------------------");

		test.estacionarMotos(e);

		System.out.println("-------------------------------------------------------------");

		test.retirarAutos(e);

		System.out.println("-------------------------------------------------------------");

		test.retirarMotos(e);

		System.out.println("-------------------------------------------------------------");

		e.finalizarDia();

	}

	private void estacionarMotos(Estacionamiento e) {
		ArrayList<VehiculoDTO> motos = new ArrayList<Test.VehiculoDTO>();

		motos.add(new VehiculoDTO("324ADS", 10, 55));
		motos.add(new VehiculoDTO("654grt", 0, 87));
		motos.add(new VehiculoDTO("444fef", 24, 0));
		motos.add(new VehiculoDTO("432htf", 15, 25));
		motos.add(new VehiculoDTO("2ff444", 12, 5));
		motos.add(new VehiculoDTO("675BGf", 9, 15));
		motos.add(new VehiculoDTO("894NNC", 7, 10));
		motos.add(new VehiculoDTO("321HRS", 14, 15));

		try {
			for (VehiculoDTO vehiculoDTO : motos) {
				e.estacionar(TipoVehiculo.MOTOCICLETA, vehiculoDTO.getPatente(), vehiculoDTO.getHora());
			}
		} catch (IllegalArgumentException e3) {
			System.out.println( e3.getMessage());

		}

	}

	private void estacionarAutos(Estacionamiento e) {

		ArrayList<VehiculoDTO> autos = new ArrayList<Test.VehiculoDTO>();

		autos.add(new VehiculoDTO("fff444", 10, 0));
		autos.add(new VehiculoDTO("fff424", 0, 77));
		autos.add(new VehiculoDTO("fef444", 27, 0));
		autos.add(new VehiculoDTO("ogy384", 12, 5));
		autos.add(new VehiculoDTO("2ff444", 12, 5));
		autos.add(new VehiculoDTO("BGf444", 20, 55));
		autos.add(new VehiculoDTO("NNC894", 6, 25));
		autos.add(new VehiculoDTO("HRS875", 7, 25));
		autos.add(new VehiculoDTO("LTC824", 11, 25));
		autos.add(new VehiculoDTO("WRG833", 14, 45));

		for (VehiculoDTO vehiculoDTO : autos) {
			try {
				e.estacionar(TipoVehiculo.AUTO, vehiculoDTO.getPatente(), vehiculoDTO.getHora());
		} catch (IllegalArgumentException e1) {
			System.out.println("No se pudo estacionar el veh�culo - " + e1.getMessage());
		} catch (RuntimeException ex) {
			System.out.println("No se pudo estacionar el veh�culo - " + ex.getMessage());
		}
	}
	}

		
	private void retirarAutos(Estacionamiento e) {
		float importe;
		ArrayList<VehiculoDTO> autos = new ArrayList<Test.VehiculoDTO>();

		autos.add(new VehiculoDTO("BGf444", 23, 10));
		autos.add(new VehiculoDTO("BGf444", 23, 25));
		autos.add(new VehiculoDTO("LTC824", 2, 25));
		autos.add(new VehiculoDTO("NNC894", 14, 13));
		autos.add(new VehiculoDTO("HRS875", 18, 40));

		try {
			for (VehiculoDTO vehiculoDTO : autos) {

				importe = e.retirar(vehiculoDTO.getPatente(), vehiculoDTO.getHora());
				System.out.printf("Se retir� correctamente el veh�culo patente %s, debe abonar $ %4.2f \n",
						vehiculoDTO.getPatente(), importe);
			}
		} catch (Exception e1) {
			System.out.println( e1.getMessage());
		}

	}

	private void retirarMotos(Estacionamiento e) {
		float importe;
		ArrayList<VehiculoDTO> motos = new ArrayList<Test.VehiculoDTO>();

		motos.add(new VehiculoDTO("432htf", 18, 10));
		motos.add(new VehiculoDTO("432htf", 18, 25));
		motos.add(new VehiculoDTO("675BGf", 20, 05));
		motos.add(new VehiculoDTO("321HRS", 17, 54));

		try {
			for (VehiculoDTO vehiculoDTO : motos) {

				importe = e.retirar(vehiculoDTO.getPatente(), vehiculoDTO.getHora());
				System.out.printf("Se retir� correctamente el veh�culo patente %s, debe abonar $ %4.2f \n",
						vehiculoDTO.getPatente(), importe);
			}
		} catch (Exception e1) {
			System.out.println( e1.getMessage());
		}
	}

	private class VehiculoDTO {

		private String patente;
		private Hora hora;;

		/**
		 * @param patente
		 * @param hora
		 * @param minuto
		 */
		public VehiculoDTO(String patente, int hora, int minuto) {
			super();
			this.patente = patente;
			this.hora = new Hora(hora, minuto);
		}

		/**
		 * @return the patente
		 */
		public String getPatente() {
			return patente;
		}

		/**
		 * @return the hora
		 */
		public Hora getHora() {
			return hora;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "VehiculoDTO [patente=" + patente + ", hora=" + hora + "]";
		}

	}
}
