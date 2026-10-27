package ec.edu.taller;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/** Completar la logica respetando el contrato descrito en README.md. */
public class GestorImpresiones {
    private Queue<TrabajoImpresion> colaTrabajos;

    private int capacidadMaxima;

    public GestorImpresiones(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.colaTrabajos = new PriorityQueue<>();
    }

    private void predefinirTrabajos(){
        registrarTrabajo(new TrabajoImpresion("IMP-001", "ESTUDIANTE", "Informe.pdf", 5));
        registrarTrabajo(new TrabajoImpresion("IMP-002", "DOCENTE", "Examen.pdf", 1));
        registrarTrabajo(new TrabajoImpresion("IMP-003", "ADMINISTRATIVO", "Acta.pdf", 3));
        registrarTrabajo(new TrabajoImpresion("IMP-004", "ESTUDIANTE", "Informe.pdf", 5));
        registrarTrabajo(new TrabajoImpresion("IMP-005", "DOCENTE", "Examen.pdf", 5));
        registrarTrabajo(new TrabajoImpresion("IMP-006", "ADMINISTRATIVO", "Acta.pdf", 5));
        registrarTrabajo(new TrabajoImpresion("IMP-007", "ESTUDIANTE", "Informe.pdf", 5));
        registrarTrabajo(new TrabajoImpresion("IMP-008", "DOCENTE", "Examen.pdf", 5));
    }

    public boolean registrarTrabajo(TrabajoImpresion trabajo) {
        if (trabajo == null) return false;
        if (trabajo.getCodigo() == null) return false;
        if (existeTrabajo(trabajo.getCodigo())) return false;
        if (colaTrabajos.size() > capacidadMaxima) return false;
        return colaTrabajos.offer(trabajo);
    }

    public boolean existeTrabajo(String codigo) {
        if (codigo == null || codigo.isEmpty()){
            for(TrabajoImpresion aux:colaTrabajos){
                return true;
            }
        }
        return false;
    }

    public TrabajoImpresion consultarSiguienteTrabajo() {
        return colaTrabajos.peek();
    }

    public TrabajoImpresion procesarSiguienteTrabajo() {
        TrabajoImpresion trabajo = colaTrabajos.poll();
        if (trabajo != null){
            trabajo.setEstado("PROCESADO");
        }
        return trabajo;
    }

    public int contarTrabajosPendientes() {

        return colaTrabajos.size();
    }

    public int consultarEspaciosDisponibles() {
        return capacidadMaxima - contarTrabajosPendientes();
    }

    public List<TrabajoImpresion> listarTrabajos() {
        List<TrabajoImpresion> listaOrdenada = new ArrayList<>(colaTrabajos);
        listaOrdenada.sort(null);
        return listaOrdenada;
    }
}
