import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.*;
import service.*;


public class Main {
    public static void main(String[] args) {

        Equipamento bola = new Equipamento("bola", "bola", 2, 10, Equipamento.Condicao.DISPONIVEL);
        Equipamento raquete = new Equipamento("raquete", "equipamento", 2, 10, Equipamento.Condicao.DISPONIVEL);
        List<Equipamento> equipamentos = new ArrayList<>();
        equipamentos.add(bola);
        equipamentos.add(raquete);
        Cliente cliente = new Cliente("Ana", "123.456.789-00", "ana@gmail.com", "1234556");
        Cliente cliente2 = new Cliente("carlos", "4567", "caarlos@gmail.com", "45967");
        LocalEsportivo quadra = new LocalEsportivo("Quadra A", "Futebol", 20, 150.0, LocalEsportivo.Condicao.DISPONIVEL, equipamentos);
        LocalEsportivo campo = new LocalEsportivo("campinho", "volei", 30, 200.0, LocalEsportivo.Condicao.DISPONIVEL, equipamentos);
        ReservaService service = new ReservaService();

        Reserva reserva1 = new Reserva(1, cliente, Reserva.StatusReserva.ATIVA, quadra, LocalDateTime.now(), LocalDateTime.now().plusDays(1), equipamentos);
        Reserva reserva2 = new Reserva(2, cliente2, Reserva.StatusReserva.ATIVA, campo, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), equipamentos);
        service.criarReserva(reserva2);
        service.criarReserva(reserva1);
        service.cancelarReserva(reserva1);
        service.cancelarReserva(reserva2);

    }
}
