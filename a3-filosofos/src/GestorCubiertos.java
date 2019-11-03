public class GestorCubiertos {
    boolean cubiertos[];

    public GestorCubiertos(int numCubiertos){
        cubiertos = new boolean[numCubiertos];
        for (int i = 0; i < numCubiertos; i++){
            cubiertos[i] = true;
        }
    }

    public synchronized boolean cogerCubiertos(int pos1, int pos2)
    {
        boolean success=false;

        if ((cubiertos[pos1]) && (cubiertos[pos2]))
        {
            cubiertos[pos1] = false;
            cubiertos[pos2] = false;
            success=true;
        }

        return success;
    }

    public void liberarCubiertos(int pos1, int pos2){
        cubiertos[pos1] = true;
        cubiertos[pos2] = true;
    }
}
