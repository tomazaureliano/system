package service;

import model.Reserva;
import service.ReservaService;
import java.util.ArrayList;
import java.util.List;

public class RelatorioService {




    public List<Reserva> listarReservas(Reserva reserva) {
        List<Reserva> reservas = getReservas();
        System.out.println("Reservas listado");
        for(Reserva reserva : reservas){
            System.out.println(reserva.getIdReserva());
            System.out.println(reserva.getLocal().getNome());
            System.out.println(reserva.getLocal().getCondicao());
            System.out.println(reserva.getNomeEquipamentos());

        }

        return reservas;

    }
}
