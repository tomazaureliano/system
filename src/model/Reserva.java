package model;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Reserva {
    private int idReserva;
    private Cliente cliente;
    private List<Equipamento> equipamentos;
    private LocalEsportivo local;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private StatusReserva status;
    int convidados;
    private long valor;
    public enum StatusReserva {
    ATIVA, CANCELADA, FINALIZADA
    }
    public Reserva(int idReserva, int convidados, Cliente cliente, StatusReserva status, LocalEsportivo local, LocalDateTime inicio, LocalDateTime fim, List<Equipamento> equipamentos) {
        this.idReserva = idReserva;
        this.cliente = cliente;
        this.status = status;
        this.local = local;
        this.inicio = inicio;
        this.fim = fim;
        this.equipamentos = equipamentos;
        this.status = StatusReserva.ATIVA;
        this.convidados = convidados;
        for(Equipamento equipamento : equipamentos){
            valor += (long) equipamento.getValor();
        }
        valor += (long) local.getValor();
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }
    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }
    public LocalEsportivo getLocal() {
        return local;
    }
    public void setLocal(LocalEsportivo local) {
        this.local = local;
    }
    public LocalDateTime getInicio() {
        return inicio;
    }
    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }
    public LocalDateTime getFim() {
        return fim;
    }
    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }
    public StatusReserva getStatus() {
        return status;
    }
    public void setStatus(StatusReserva status) {
        this.status = status;
    }
   public boolean estaAtiva(){
        return status == StatusReserva.ATIVA;
   }
   public void cancelar(){
       this.status = StatusReserva.CANCELADA;
   }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public int getConvidados() {
        return convidados;
    }

    public void setConvidados(int convidados) {
        this.convidados = convidados;
    }
}