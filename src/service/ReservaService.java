package service;
import model.Reserva;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaService {
    private List<Reserva> reservas = new ArrayList<>();
    private static final long HORAS_CANCELAMENTO_LIMITE = 2;
    public boolean criarReserva(Reserva novaReserva){
        for(Reserva reserva : reservas){
            if(reserva.getLocal().equals(novaReserva.getLocal()) && reserva.estaAtiva() && horariosConflitam(reserva.getInicio(), reserva.getFim(), novaReserva.getInicio(), novaReserva.getFim())){
             System.out.println("local já reservado");
             return false;
            }
        }
        if(novaReserva.getConvidados() > novaReserva.getLocal().getCapacidade()){
            System.out.println("Convidados excedem capacidade do local");
        }
        reservas.add(novaReserva);
        System.out.println("Reserva Criada");
        for(Reserva reserva : reservas){
            System.out.println(STR."Reserva com id: \{reserva.getIdReserva()}");
        }


        return true;
    }
    public void cancelarReserva(Reserva reserva) {
        LocalDateTime agora = LocalDateTime.now();
        long horasFaltando = Duration.between(agora, reserva.getInicio()).toHours();

        if (horasFaltando < HORAS_CANCELAMENTO_LIMITE) {
            System.out.println("❌ Cancelamento tarde");
            System.out.println("Aplicando multa...");
            reserva.setValor((reserva.getValor() + horasFaltando));

        }

        reserva.cancelar();
        System.out.println("✅ Reserva cancelada com sucesso!");
    }
    private boolean horariosConflitam(LocalDateTime inicio1, LocalDateTime fim1,
                                      LocalDateTime inicio2, LocalDateTime fim2) {
        return (inicio1.isBefore(fim2) && fim1.isAfter(inicio2));
    }
    public List<Reserva> listarReservas(){
        return reservas;
    }
}
