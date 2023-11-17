package aed;

public class Heap {
    private Router[] _heap;

    public Heap(int longitud){
        _heap = new Router[longitud];
    }

    public Heap(Router[] lista){
        _heap = lista;
        BuildMaxHeap();
    }


    public Router desencolar(){
        Router max = _heap[0];
        Router temp =_heap[_heap.length-1];
        _heap[0] = temp;
        _heap[_heap.length-1] = new Router(-1, -1);
        maxHeapify(0);
        return max;
    }


    private void BuildMaxHeap(){
        for(int i=((_heap.length)/2)-1;i>=0;i--){
                maxHeapify(i);
            }
    }

    private void maxHeapify(int i){
        int largest=i;
        int l = izquierdo(i);
        int r = derecho(i);

        if(l<_heap.length && _heap[l].getTrafico()>_heap[i].getTrafico()){
            largest = l;
        } 

        if(r<_heap.length && _heap[r].getTrafico()>_heap[largest].getTrafico()){
            largest = r;
        } 

        if(largest !=i){
            Router temp  = _heap[i];
            _heap[i] = _heap[largest];
            _heap[largest] = temp; 
            maxHeapify(largest);
        }
    }

    private int izquierdo(int posicionNodo){
        return 2*posicionNodo+1;
    }

    private int derecho(int posicionNodo){  
        return 2*posicionNodo+2;
    }

    private int padre(int posicionNodo){
        return (posicionNodo-1)/(2);
    }
}

