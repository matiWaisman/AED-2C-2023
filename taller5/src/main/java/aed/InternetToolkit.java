package aed;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class InternetToolkit {
    public InternetToolkit() {
    }

    // Idea: insertion sort para que el mejor caso sea O(N)
    public Fragment[] tcpReorder(Fragment[] fragments) {
        int size = fragments.length;
        for(int i = 1; i < size; i++){
            Fragment valorActual = fragments[i];
            int j = i - 1;
            while(j >= 0 && fragments[j].compareTo(valorActual) > 0){
                fragments[j+1]._id = fragments[j]._id;
                j --;
            }
            fragments[j + 1] = valorActual;
        }
        return fragments;
    }

    // Idea: Armo el heap y devuelvo los primeros K elementos
    public Router[] kTopRouters(Router[] routers, int k, int umbral) {
        Router[] res = new Router[k];
        Heap heap = new Heap(routers);
        Router elementoActual = heap.desencolar();
        int iterador = 0;
        while(iterador < k && elementoActual.getTrafico() > umbral){
            res[iterador] = elementoActual;
            elementoActual = heap.desencolar();
            iterador ++;
        }
        return res;
    }

    // Idea: Usar radix para sortear por los 4 octetos
    public IPv4Address[] sortIPv4(String[] ipv4) {
        IPv4Address[]res = new IPv4Address[ipv4.length];
        for (int i = 0; i < ipv4.length; i++){
            res[i] = new IPv4Address(ipv4[i]);
        }
        for(int i = 3; i >= 0; i--){
            Queue<IPv4Address>[] buckets = bucketear(res, i);
            res = desbucketear(buckets, res.length);
        }
        return res;
    }

    public Queue<IPv4Address>[] bucketear(IPv4Address[] listaOriginal, int octetoActual) {
        Queue<IPv4Address>[] buckets = new LinkedList[256];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        for (int i = 0; i < listaOriginal.length; i++) {
            int elementoActual = listaOriginal[i].getOctet(octetoActual);
            buckets[elementoActual].offer(listaOriginal[i]);
        }
        return buckets;
    }

    public IPv4Address[] desbucketear(Queue<IPv4Address>[] buckets, int cantidadDeElementos) {
        IPv4Address[] res = new IPv4Address[cantidadDeElementos];
        int cantidadAgregada = 0;
        int i = 0;
        while (i < buckets.length && cantidadAgregada != cantidadDeElementos) {
            while (!buckets[i].isEmpty()) {
                res[cantidadAgregada] = buckets[i].poll();
                cantidadAgregada++;
            }
            i++;
        }
        return res;
    }

}
