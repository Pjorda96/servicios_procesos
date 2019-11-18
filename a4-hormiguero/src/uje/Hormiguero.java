package uje;

public class Hormiguero implements Runnable{
	public static int numeroGaleriaObrera;
	public static int numeroGaleriaCuidadora;
	public static int numeroGaleriaGuerrera;
	
	public static int numeroHuevosGaleriasObrera;
	public static int numeroHuevosGaleriasCuidadora;
	public static int numeroHuevosGaleriasGuerrera;
	
	@Override
	public void run() {
		while(true) {
			
			/*
			 * Comprobamos que sean divisibles y no esten a 0,
			 * para avisar de la creaci�n de 5 galerias nuevas
			 */
			if (numeroGaleriaObrera % 5 == 0 && numeroGaleriaObrera != 0) {
				System.out.println( "Nueva galeria obrera. Total: " + numeroGaleriaObrera );
			}
			if (numeroGaleriaCuidadora % 5 == 0 && numeroGaleriaCuidadora != 0) {
				System.out.println( "Nueva galeria obrera. Total: " + numeroGaleriaObrera );
			}
			if (numeroGaleriaGuerrera % 5 == 0 && numeroGaleriaGuerrera != 0) {
				System.out.println( "Nueva galeria obrera. Total: " + numeroGaleriaObrera );
			}
		}
	}
}
