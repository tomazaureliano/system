package service;
import model.Equipamento;
import model.LocalEsportivo;
import model.Reserva;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReservaService {
    private List<Reserva> reservas = new ArrayList<>();
    private static final long HORAS_CANCELAMENTO_LIMITE = 2;

    public boolean criarReserva(Reserva novaReserva) {
       if(novaReserva.getConvidados() > novaReserva.getLocal().getCapacidade()){
           System.out.println("Convidados excedem capacidade");
           return false;
       }
       for(Reserva reservaExistente : reservas){
           if(reservaExistente.getLocal().equals(novaReserva.getLocal()) && reservaExistente.estaAtiva() && horariosConflitam(reservaExistente.getInicio(), novaReserva.getInicio(), reservaExistente.getFim(), novaReserva.getFim())){
               System.out.println("local ja reservado");
               return false;
           }
       }
       for(Map.Entry<Equipamento, Integer> entry : novaReserva.getEquipamentos().entrySet()){
           Equipamento equipamento = entry.getKey();
           int quantidadeReservada = entry.getValue();
           if(quantidadeReservada > equipamento.getQuantidade()){
               throw new IllegalArgumentException("Erro: Tentando reservar" + quantidadeReservada + " unidades de" + equipamento.getNome() + ", mas só há" + equipamento.getQuantidade() + " disponiveis");

           }
       }
        reservas.add(novaReserva);
        System.out.println("Reserva Criada");
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

    public List<Reserva> getReservas() {
        return reservas;
    }
}
